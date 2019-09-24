package com.web.account.configuration;

import com.alibaba.druid.filter.logging.LogFilter;
import com.mysql.cj.Session;
import com.web.account.shiro.UserRealm;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.cache.ehcache.EhCacheManager;
import org.apache.shiro.codec.Base64;
import org.apache.shiro.session.SessionListener;
import org.apache.shiro.session.mgt.SessionManager;
import org.apache.shiro.session.mgt.eis.MemorySessionDAO;
import org.apache.shiro.session.mgt.eis.SessionDAO;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.filter.authc.LogoutFilter;
import org.apache.shiro.web.mgt.CookieRememberMeManager;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.servlet.SimpleCookie;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @Author luotao
 */
@Configuration
public class ShiroConfiguration {

    /**
     * 创建ShiroFilterFactoryBean
     */
    @Bean
    public ShiroFilterFactoryBean getShiroFilterFactoryBean(@Qualifier("securityManager") DefaultWebSecurityManager securityManager){
        ShiroFilterFactoryBean shiroFilter = new ShiroFilterFactoryBean();
        //设置安全管理器
        shiroFilter.setSecurityManager(securityManager);
        /**
         * shiro内置过滤器,可以实现权限相关的拦截器
         *  常用的过滤器:
         *      anon: 无需认证(登录)可以访问
         *      authc: 必须认证才可以访问
         *      user:如果使用rememberMe的功能可以直接访问
         *      perms: 该资源必须得到资源权限才可以访问
         *      role: 该资源必须得到角色权限才可以访问
         */
        //必须是LinkedHashMap,因为拦截器必须保证是有序的
        Map<String,String> filterMap =  new LinkedHashMap<String, String>();
        //限制同一账号在同时在线的个数
//        LogoutFilter logoutFilter = new LogoutFilter();
//        logoutFilter.setRedirectUrl("/login");
//        filterMap.put("/logout" , null);
        //设置需要拦截的页面(默认跳转到内部的login界面)
        //第一个参数是需要拦截的完整的路径,后面的过滤器的种类
        filterMap.put("/api/cron" , "authc");
        filterMap.put("/api/login" , "anon");
        //可以使用统配的方法进行统一拦截
//        filterMap.put("/*" , "authc");
        //放行,填写需要放行的路径,后面的参数设置为anon
//        filterMap.put("/路径" , "anon");


        //授权过滤器
        filterMap.put("api/cron", "perms[user:cron]");
        //设置未授权提示页面
        //参数:路径
        shiroFilter.setUnauthorizedUrl("/unAuth");

        filterMap.put("/*" , "authc");

        //修改跳转的默认login界面
        shiroFilter.setLoginUrl("/login");

        shiroFilter.setFilterChainDefinitionMap(filterMap);
        return shiroFilter;
    }

    /* ======================Cookie对象管理====================== */
    private static final String COOKIE_NAME = "rememberMe";

    public SimpleCookie rememberMeCookie(){
        SimpleCookie simpleCookie = new SimpleCookie(COOKIE_NAME);
        //cookie生效时间7天
        simpleCookie.setMaxAge(604800);
        return simpleCookie;
    }
    /* cookie管理对象 : 记住我功能 */
    public CookieRememberMeManager rememberMeManager(){
        CookieRememberMeManager cookieRememberMeManager = new CookieRememberMeManager();
        cookieRememberMeManager.setCookie(rememberMeCookie());
        //rememberMe cookie加密的密钥 建议每个项目都不一样 默认AES算法 密钥长度(128 256 512 位)
        cookieRememberMeManager.setCipherKey(Base64.decode("3AvVhmFLUs0KTA3Kprsdag=="));
        return cookieRememberMeManager;
    }

    @Bean
    public SessionDAO sessionDAO(){
        return new MemorySessionDAO();
    }

    public SessionManager sessionManager(){
        DefaultWebSessionManager sessionManager = new DefaultWebSessionManager();
        Collection<SessionListener> listeners = new ArrayList<>();
        sessionManager.setSessionListeners(listeners);
        sessionManager.setSessionDAO(sessionDAO());
        return sessionManager;
    }

    /**
     * SecurityManager : 核心安全事务管理器 , 权限管理
     * 这个类组合了登陆 , 登出 , 权限 , session的处理 . 是个比较重要的类 .
     */

    /**
     * 创建DefaultWebSecurtyManager
     */
    @Bean(name="securityManager")
    public DefaultWebSecurityManager getDefaultWebSecurtyManager(@Qualifier("userRealm") UserRealm userRealm){
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        //关联realm
        securityManager.setRealm(userRealm);
//        //用户授权/认证信息Cache, 采用EhCache 缓存
//        securityManager.setCacheManager(ehCacheManager());

        // 自定义session管理 使用redis
        securityManager.setSessionManager(sessionManager());

        //注入记住我管理器;
        securityManager.setRememberMeManager(rememberMeManager());
        return securityManager;
    }

//    /**
//     * EhCacheManager , 缓存管理
//     * 用户登陆成功后 , 把用户信息和权限信息缓存起来 , 然后每次用户请求时 , 放入用户的session中 , 如果不设置这个bean , 每个请求都会查询一次数据库 .
//     */
//    @Bean
//    public EhCacheManager ehCacheManager() {
//        EhCacheManager em = new EhCacheManager();
//        //配置文件路径
//        em.setCacheManagerConfigFile("classpath:config/ehcache.xml");
//        return em;
//    }


    /**
     * HashedCredentialsMatcher , 这个类是为了对密码进行编码的 ,
     * 防止密码在数据库里明码保存 , 当然在登陆认证的时候 ,
     * 这个类也负责对form里输入的密码进行编码
     * 处理认证匹配处理器：如果自定义需要实现继承HashedCredentialsMatcher
     */
    @Bean(name = "hashedCredentialsMatcher")
    public HashedCredentialsMatcher hashedCredentialsMatcher() {
        HashedCredentialsMatcher credentialsMatcher = new HashedCredentialsMatcher();
        //指定加密方式方式，也可以在这里加入缓存，当用户超过五次登陆错误就锁定该用户禁止不断尝试登陆
        credentialsMatcher.setHashAlgorithmName("MD5");
        credentialsMatcher.setHashIterations(2);
        credentialsMatcher.setStoredCredentialsHexEncoded(true);
        return credentialsMatcher;
    }

    /**
     * 创建Realm
     */
    @Bean(name="userRealm")
    public UserRealm getRealm(){
        return new UserRealm();
    }
}
