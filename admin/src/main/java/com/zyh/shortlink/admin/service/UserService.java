package com.zyh.shortlink.admin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zyh.shortlink.admin.dao.entity.UserDO;
import com.zyh.shortlink.admin.dto.req.UserRegisterReqDTO;
import com.zyh.shortlink.admin.dto.resp.UserRespDTO;

/**
 *author 邹宇航  @vision 1.0
 */
public interface UserService extends IService<UserDO> {

    /**
     * 获取用户名
     * @param username
     * @return
     */
    UserRespDTO getUserByUserName(String username);

    /**
     * 用户是否存在
     * @param username
     * @return
     */
    Boolean hasUsername(String username);

    /**
     * 注册
     * @param requestParam
     * @return
     */
    void register(UserRegisterReqDTO requestParam);
}
