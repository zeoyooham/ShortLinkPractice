package com.zyh.shortlink.admin.convention.result;

import com.zyh.shortlink.admin.convention.errorcode.BaseErrorCode;
import com.zyh.shortlink.admin.convention.exception.AbstractException;

import java.util.Optional;

/**
 * author 邹宇航  @vision 1.0
 */
public final class Results {

    public static Result<Void> success(){
        return new Result<Void>().setCode(Result.SUCCESS_CODE);
    }

    public static <T> Result<T> success(T data){
        return new Result<T>().setData(data).setCode(Result.SUCCESS_CODE);
    }

    /**
     * 服务端异常
     * @return
     */
    public static Result<Void> failure(){
        return new Result<Void>().setCode(BaseErrorCode.SERVICE_ERROR.code());
    }


    /**
     * 自定义异常
     * @param abstractException
     * @return
     */
    public static Result<Void> failure(AbstractException abstractException){
        String errorCode= Optional.ofNullable(abstractException.getErrorCode()).orElse(BaseErrorCode.SERVICE_ERROR.code());
        String errorMessage=Optional.ofNullable(abstractException.getErrorMessage()).orElse(BaseErrorCode.SERVICE_ERROR.message());
        return new Result<Void>().setCode(errorCode).setMessage(errorMessage);
    }

    /**
     * 手动抛出异常
     * @param errorCode
     * @param errorMessage
     * @return
     */
    public static Result<Void> failure(String errorCode,String errorMessage){
        return new Result<Void>().setCode(errorCode).setMessage(errorMessage);
    }
}
