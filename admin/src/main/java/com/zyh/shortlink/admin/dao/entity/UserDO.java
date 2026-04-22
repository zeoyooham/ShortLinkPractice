package com.zyh.shortlink.admin.dao.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.zyh.shortlink.admin.common.database.BaseDO;
import lombok.Data;

/**
 * author 邹宇航  @vision 1.0
 */
@Data
@TableName("t_user")
public class UserDO extends BaseDO {

    private Long id;

    private String username;

    private String password;

    private String realName;

    private String phone;

    private String mail;

    private Data deletionTime;
}
