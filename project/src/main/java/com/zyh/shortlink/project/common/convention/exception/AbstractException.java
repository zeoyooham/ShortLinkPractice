package com.zyh.shortlink.project.common.convention.exception;

/**
 * author 邹宇航  @vision 1.0
 */

import com.oracle.truffle.api.library.GenerateLibrary;
import com.zyh.shortlink.project.common.convention.errorcode.IErrorCode;
import lombok.Getter;
import org.springframework.util.StringUtils;

import java.util.Optional;

@Getter
public abstract class AbstractException extends RuntimeException{

    public final String errorCode;

    public final String errorMessage;

    public AbstractException(String message, Throwable throwable, IErrorCode errorCode) {
        super(message, throwable);
        this.errorCode = errorCode.code();
        this.errorMessage = Optional.ofNullable(StringUtils.hasLength(message) ? message : null).orElse(errorCode.message());
    }
}
