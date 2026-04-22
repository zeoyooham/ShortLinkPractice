package com.zyh.shortlink.admin.common.constant;

/**
 * author 邹宇航  @vision 1.0
 */
public class RedisCacheConstant {

    /**
     * 用户注册标识
     */
    public static final String LOCK_USER_REGISTER_KEY = "short-link:lock_user-register:";

    /**
     * 分组创建标识
     */
    public static final String LOCK_GROUP_CREATE_KEY = "short-link:lock_group-create:%s";
}
