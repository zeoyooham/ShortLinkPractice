package com.zyh.shortlink.admin.dto.req;

import lombok.Data;

/**
 * author 邹宇航  @vision 1.0
 */
@Data
public class UserRegisterReqDTO {

    private String username;

    private String password;

    private String realName;

    private String phone;

    private String mail;
}
