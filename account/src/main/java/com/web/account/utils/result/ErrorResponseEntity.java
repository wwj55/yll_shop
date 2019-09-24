package com.web.account.utils.result;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @Author luotao
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ErrorResponseEntity implements BaseResponseEntity {
    /**
     * http响应状态码
     */
    private ResponseStatus status;
    /**
     * 前端显示的信息
     */
    private String msg;
    /**
     * 错误的信息
     */
    private String error;
    /**
     * 异常的信息
     */
    private String exception;

    private String path;
    /**
     * 抛出错误时间
     */
    private Date timestamp;

    public static ErrorResponseEntity failure(ResponseStatus responseStatus , Throwable e){
        return  ErrorResponseEntity.builder()
                .msg(responseStatus.getMsg())
                .error(e.getMessage())
                .timestamp(new Date())
                .exception(e.getClass().getName())
                .build();
    }
}
