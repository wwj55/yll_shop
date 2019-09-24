package com.web.account.utils.exception;

import com.web.account.utils.result.BaseResponseEntity;
import com.web.account.utils.result.ResponseEntity;
import com.web.account.utils.result.ResponseStatus;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

/**
 * @Author luotao
 */
@ControllerAdvice
@Slf4j
public class ExceptionHandlerAdvice {

    @ExceptionHandler(Exception.class)
    public BaseResponseEntity handleException(Exception e){
        if (e instanceof ServiceException) {
            return ResponseEntity.error(ResponseStatus.BUSINESS_UNKNOW_ERROR);
        } else if (e instanceof NoHandlerFoundException) {
            return ResponseEntity.error(ResponseStatus.NO_FOUND_ERROR);
        } else {
            return ResponseEntity.error(ResponseStatus.SYSTEM_UNKNOW_ERROR);
        }
    }


}
