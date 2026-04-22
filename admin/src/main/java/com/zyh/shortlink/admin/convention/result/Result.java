package com.zyh.shortlink.admin.convention.result;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * author 邹宇航  @vision 1.0
 */
@Data
@Accessors(chain = true)
public class Result<T> {

    @serialVersionUID
    private static final long serialVersionUID = 5679018624309023727L;

    public static final String SUCCESS_CODE="0";

    private String code;

    private String message;

    private T data;

    private String requestId;

    public boolean isSuccess(){
        return SUCCESS_CODE.equals(code);
    }

}
