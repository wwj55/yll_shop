package com.web.account.controller;

import com.web.account.utils.result.ResponseEntity;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.xml.transform.Result;

/**
 * @Author luotao
 */
@RestController
@RequestMapping("/api")
public class LoginController {

    @RequestMapping("/login")
    public ResponseEntity login(String name , String password , Model model){
        /**
         * 使用shiro编写认证操作
         */
        //1.获取subject
        Subject subject = SecurityUtils.getSubject();

        //2.封装用户数据
        UsernamePasswordToken token = new UsernamePasswordToken(name, password);

        //3,执行登录方法

        try{
            subject.login(token);
        }catch (UnknownAccountException e){
//            e.getStackTrace()
            //登录失败用户名不存在
            model.addAttribute("msg" , "用户名不存在");
            return null;
        }catch (IncorrectCredentialsException e){
            //e.getStackTrace();
            //登录失败:密码错误
            model.addAttribute("msg" , "密码错误");
            return null;
        }
        return ResponseEntity.success(token);
    }
}
