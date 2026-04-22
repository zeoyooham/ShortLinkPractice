package com.zyh.shortlink.admin.common.web;


import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.ReflectUtil;
import cn.hutool.core.util.StrUtil;
import com.esotericsoftware.minlog.Log;
import com.zyh.shortlink.admin.convention.errorcode.BaseErrorCode;
import com.zyh.shortlink.admin.convention.exception.AbstractException;
import com.zyh.shortlink.admin.convention.result.Result;
import com.zyh.shortlink.admin.convention.result.Results;
import jakarta.servlet.http.HttpServletRequest;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Objects;
import java.util.Optional;


/**
 * author 邹宇航  @vision 1.0
 * 全局异常处理器
 */
@Component("globalExceptionHandlerByAdmin")
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     *拦截参数校验异常
     * @param request
     * @param ex
     * @return
     */
    @SneakyThrows
    @ExceptionHandler(value= MethodArgumentNotValidException.class)
    private Result validExceptionHandler(HttpServletRequest request, MethodArgumentNotValidException ex){
        BindingResult bindingResult = ex.getBindingResult();
        FieldError firstFieldError = CollectionUtil.getFirst(bindingResult.getFieldErrors());
        String exceptionStr = Optional.ofNullable(firstFieldError).map(FieldError::getDefaultMessage).orElse(StrUtil.EMPTY);
        log.error("[{}] {} [ex] {}", request.getMethod(), getUrl(request), exceptionStr);
        return Results.failure(BaseErrorCode.CLIENT_ERROR.code(), exceptionStr);
    }

    /**
     * 拦截应用内异常
     * @param request
     * @param ex
     * @return
     */
    @ExceptionHandler(value= AbstractException.class)
    private Result abstractException(HttpServletRequest request,AbstractException ex){
        if(ex.getCause()!=null){
            log.error("[{}] {} [ex] {}", request.getMethod(), request.getRequestURL(), ex.toString(), ex.getCause());
        }
        log.error("[{}] {} [ex] {}", request.getMethod(), request.getRequestURL(), ex.toString());
        return Results.failure(ex);
    }

    /**
     * 拦截为捕获的异常
     * @param request
     * @param ex
     * @return
     */
    @ExceptionHandler(value= Throwable.class)
    private Result defaultErrorHandler(HttpServletRequest request, Throwable  ex){
        log.error("[{}] {} ", request.getMethod(), getUrl( request), ex.toString());
        if(Objects.equals(ex.getClass().getSuperclass().getSimpleName(),AbstractException.class.getClass().getSimpleName())){
             String errorCode= ReflectUtil.getFieldValue(ex, "errorCode").toString();
             String errorMessage= ReflectUtil.getFieldValue(ex, "errorMessage").toString();
             return Results.failure(errorCode, errorMessage);
        }
        return Results.failure();
    }

    private String getUrl(HttpServletRequest request){
        if(StringUtils.isEmpty(request.getQueryString())){
            return request.getRequestURL().toString();
        }
        return request.getRequestURL()+"?"+request.getQueryString();
    }
}
