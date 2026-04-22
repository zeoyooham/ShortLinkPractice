package com.zyh.shortlink.admin.convention.exception;

import com.zyh.shortlink.admin.convention.errorcode.IErrorCode;

/**
 * author 邹宇航  @vision 1.0
 */
public class RemoteException extends AbstractException{
    public RemoteException(String message, Throwable throwable, IErrorCode errorCode) {
        super(message, throwable, errorCode);
    }

    public RemoteException(String message, IErrorCode errorCode) {
        super(message, null, errorCode);
    }

    public RemoteException(IErrorCode errorCode) {
        super(null,null,errorCode);
    }

    public RemoteException(String message) {
        super(message, null, null);
    }

    @Override
    public String toString() {
        return "RemoteException{" +
                "code='" + errorCode + "'," +
                "message='" + errorMessage + "'" +
                '}';
    }
}
