package com.zyh.shortlink.admin.convention.exception;

import com.zyh.shortlink.admin.convention.errorcode.IErrorCode;
import lombok.Getter;
import org.springframework.util.StringUtils;
import java.util.Optional;

/**
 * author 邹宇航  @vision 1.0
 */
@Getter
public abstract class AbstractException extends RuntimeException{

    public String errorCode;

    public String errorMessage;

    public AbstractException(String message, Throwable throwable, IErrorCode errorCode){
        super(message, throwable);
        this.errorCode=errorCode.code();
        this.errorMessage= Optional.ofNullable(StringUtils.hasLength(message)?message:null).orElse(errorCode.message());
    }
}
