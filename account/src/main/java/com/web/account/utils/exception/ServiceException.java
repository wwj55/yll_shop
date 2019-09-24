package com.web.account.utils.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @Author luotao
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class ServiceException extends RuntimeException{

    private ResponseStatus status;

    public ServiceException(ResponseStatus responseStatus){
        this.status = responseStatus;
    }

    public ServiceException(Throwable cause , ResponseStatus responseStatus){
        super(cause);
        this.status = responseStatus;
    }
}
