package com.zyh.shortlink.admin.dto.resp;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.zyh.shortlink.admin.common.serialize.PhoneDesensitizationSerializer;
import lombok.Data;

/**
 * author 邹宇航  @vision 1.0
 */
@Data
public class UserRespDTO {

    private Long id;

    private String username;

    private String realName;

    @JsonSerialize(using= PhoneDesensitizationSerializer.class)
    private String phone;

    private String mail;
}
