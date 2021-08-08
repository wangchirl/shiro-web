package com.shadow.shiro.exception;

import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import com.shadow.shiro.vo.R;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.DisabledAccountException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authz.UnauthorizedException;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.multipart.support.MissingServletRequestPartException;

/**
 * 全局异常处理器
 */
@RestControllerAdvice(basePackages = "com.shadow.shiro.controller")
@Slf4j
public class ExceptionHandlerAdvise {

    @ExceptionHandler(UnknownAccountException.class)
    public R unknownAccountException(UnknownAccountException e) {
        return R.error("未知的用户");
    }

    @ExceptionHandler(IncorrectCredentialsException.class)
    public R incorrectCredentialsException(IncorrectCredentialsException e) {
        return R.error("密码错误");
    }

    @ExceptionHandler(UnauthorizedException.class)
    public R unauthorizedException(UnauthorizedException e) {
        return R.error("未授权的操作");
    }

    @ExceptionHandler(DisabledAccountException.class)
    public R disabledAccountException(DisabledAccountException e) {
        return R.error("账号不可用");
    }

    @ExceptionHandler(LockedAccountException.class)
    public R lockedAccountException(LockedAccountException e) {
        return R.error("账号被锁定");
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public R methodArgumentNotValidException(MethodArgumentNotValidException e) {
        BindingResult bindingResult = e.getBindingResult();
        StringBuilder sb = new StringBuilder();
        bindingResult.getFieldErrors().forEach(fieldError -> {
            sb.append(fieldError.getDefaultMessage());
        });
        return R.error(sb.toString());
    }

    @ExceptionHandler(MissingServletRequestPartException.class)
    public R missingServletRequestPartException(MissingServletRequestPartException e) {
        return R.error("缺失必要的参数 ：" + e.getMessage());
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public R illegalArgumentException(IllegalArgumentException e) {
        return R.error("不合法的请求参数：" + e.getMessage());
    }

    @ExceptionHandler(ServiceException.class)
    public R serviceException(ServiceException e) {
        return R.error(e.getMessage());
    }

    @ExceptionHandler(value = {HttpMessageNotReadableException.class,InvalidFormatException.class})
    public R readableException(Exception e) {
        return R.error("参数类型异常");
    }

    @ExceptionHandler(Exception.class)
    public R exception(Exception e) {
        return R.error("系统异常");
    }
}
