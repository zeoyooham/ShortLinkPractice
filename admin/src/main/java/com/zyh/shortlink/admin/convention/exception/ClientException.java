package com.zyh.shortlink.admin.convention.exception;

import com.zyh.shortlink.admin.convention.errorcode.IErrorCode;

/**
 * author 邹宇航  @vision 1.0
 */
public class ClientException extends AbstractException{

    public ClientException(IErrorCode errorCode) {
        this(errorCode.message(), null, errorCode);
    }

    public ClientException(String message) {
        this(message, null, null);
    }

    public ClientException(String message, IErrorCode errorCode) {
        super(message, null, errorCode);
    }

    public ClientException(String message, Throwable throwable, IErrorCode errorCode) {
        super(message, throwable, errorCode);
    }

    @Override
    public String toString() {
        return "ClientException{" +
                "code='" + errorCode + "'," +
                "message='" + errorMessage + "'" +
                '}';
    }
}
