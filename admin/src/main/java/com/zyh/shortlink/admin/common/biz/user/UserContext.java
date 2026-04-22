package com.zyh.shortlink.admin.common.biz.user;

import com.alibaba.ttl.TransmittableThreadLocal;

import java.util.Optional;

/**
 * author 邹宇航  @vision 1.0
 * 用户上下文
 */
public final class UserContext {

    private static final ThreadLocal<UserInfoDTO> USER_CONTEXT=new TransmittableThreadLocal<>();

    /**
     * 设置用户上下文
     * @param user
     */
    public static void setUser(UserInfoDTO user){
        USER_CONTEXT.set(user);
    }

    /**
     * 获取用户id
     * @return
     */
    public static String getUserId(){
        UserInfoDTO user= USER_CONTEXT.get();
        return Optional.ofNullable(user).map(UserInfoDTO::getUserId).orElse(null);
    }

    /**
     * 获取用户名
     * @return
     */
    public static String getUsername(){
        UserInfoDTO user= USER_CONTEXT.get();
        return Optional.ofNullable(user).map(UserInfoDTO::getUsername).orElse(null);
    }

    /**
     * 获取用户真实名称
     * @return
     */
    public static String getRealName(){
        UserInfoDTO user= USER_CONTEXT.get();
        return Optional.ofNullable(user).map(UserInfoDTO::getRealName).orElse(null);
    }

    /**
     * 清理用户上下文
     */
    public static void removeUser(){
        USER_CONTEXT.remove();
    }

}
