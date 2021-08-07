package com.shadow.shiro.exception;

import lombok.Data;

@Data
public class ServiceException extends RuntimeException {

    private String message;

    private int code;

    public ServiceException(String message) {
        super(message);
        this.message = message;
    }

    public ServiceException(String message, Throwable e) {
        super(message, e);
        this.message = message;
    }

    public ServiceException(String message, int code) {
        super(message);
        this.message = message;
        this.code = code;
    }

    public ServiceException(String message, int code, Throwable e) {
        super(message, e);
        this.message = message;
        this.code = code;
    }
}
