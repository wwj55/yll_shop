package com.web.account.utils.result;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

/**
 * @Author luotao
 */
@Data
@Builder
//有参构造的注解
@AllArgsConstructor
//无参构造的注解
@NoArgsConstructor
public class ResponseEntity<T> implements BaseResponseEntity {
    private T data;
    private int status;
    private String msg;

//    public SuccessResult(int status, String msg) {
//
//    }
//
//    public static SuccessResult success(){
////        此处填写参数的顺序和创建属性的顺序要一致
//        return  new SuccessResult(ResponseStatus.SUCCESS.getStatus() , ResponseStatus.SUCCESS.getMsg());
//    }
//
//    public static <T> SuccessResult success(T data){
//        return new SuccessResult(data , ResponseStatus.SUCCESS.getStatus() , ResponseStatus.SUCCESS.getMsg());
//    }

    private ResponseEntity(ResponseStatus responseStatus , T data){
        this.msg = responseStatus.getMsg();
        this.status = responseStatus.getStatus();
        this.data = data;
    }

    public static ResponseEntity success(){
        return ResponseEntity.builder()
                .status(ResponseStatus.SUCCESS.getStatus())
                .msg(ResponseStatus.SUCCESS.getMsg())
                .build();
    }

    public static <T> ResponseEntity success(T data){
        return ResponseEntity.builder()
                .data(data)
                .status(ResponseStatus.SUCCESS.getStatus())
                .msg(ResponseStatus.SUCCESS.getMsg())
                .build();
    }

    public static <T> ResponseEntity success(ResponseStatus responseStatus , T date){
        return  ResponseEntity.builder()
                .data(date)
                .status(responseStatus.getStatus())
                .msg(responseStatus.getMsg())
                .build();
    }

    public static <T> ResponseEntity success(int status , String  msg , T data){
        return ResponseEntity.builder()
                .status(status)
                .msg(msg)
                .data(data)
                .build();
    }

    /* ================错误信息的处理=============*/

    public static ResponseEntity error(){
        return ResponseEntity.builder()
                .status(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .msg(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase())
                .build();
    }

    public static ResponseEntity error(String message){
        return ResponseEntity.builder()
                .msg(message)
                .build();
    }

    public static ResponseEntity error(int status){
        return ResponseEntity.builder()
                .status(status)
                .build();
    }

    public static ResponseEntity error(int status , String msg){
        return ResponseEntity.builder()
                .status(status)
                .msg(msg)
                .build();
    }

    public static ResponseEntity error(ResponseStatus responseStatus) {
        return ResponseEntity.builder()
                .status(responseStatus.getStatus())
                .msg(responseStatus.getMsg())
                .build();
    }

    public static <T> ResponseEntity error(ResponseStatus responseStatus, T data) {
        return ResponseEntity.builder()
                .status(responseStatus.getStatus())
                .msg(responseStatus.getMsg())
                .data(data)
                .build();
    }
}
