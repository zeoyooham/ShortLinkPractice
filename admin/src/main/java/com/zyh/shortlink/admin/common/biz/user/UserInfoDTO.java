package com.zyh.shortlink.admin.common.biz.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.alibaba.fastjson2.annotation.JSONField;
/**
 * author 邹宇航  @vision 1.0
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserInfoDTO {

    /**
     * 用户id
     */
    @JSONField(name = "id")
    private String userId;

    private String username;

    private String realName;
}
