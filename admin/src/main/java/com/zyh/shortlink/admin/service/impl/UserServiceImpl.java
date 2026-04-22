package com.zyh.shortlink.admin.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zyh.shortlink.admin.common.enums.UserErrorCodeEnum;
import com.zyh.shortlink.admin.convention.exception.ClientException;
import com.zyh.shortlink.admin.convention.exception.ServiceException;
import com.zyh.shortlink.admin.dao.entity.UserDO;
import com.zyh.shortlink.admin.dao.mapper.UserMapper;
import com.zyh.shortlink.admin.dto.req.UserRegisterReqDTO;
import com.zyh.shortlink.admin.dto.resp.UserRespDTO;
import com.zyh.shortlink.admin.service.UserService;
import lombok.RequiredArgsConstructor;
import org.redisson.api.RBloomFilter;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.redisson.client.RedisClient;
import org.springframework.beans.BeanUtils;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.rmi.ServerException;
import java.util.concurrent.locks.Lock;

import static com.zyh.shortlink.admin.common.constant.RedisCacheConstant.LOCK_USER_REGISTER_KEY;
import static com.zyh.shortlink.admin.common.enums.UserErrorCodeEnum.*;

/**
 * author 邹宇航  @vision 1.0
 */
@Service
@RequiredArgsConstructor
public class UserServiceImpl extends ServiceImpl<UserMapper, UserDO> implements UserService {

   private final RBloomFilter<String> userRegisterCachePenetrationBloomFilter;
   private final RedissonClient redissonClient;
   private final GroupServiceImpl groupServiceimpl;

    @Override
    public UserRespDTO getUserByUserName(String username) {
        LambdaQueryWrapper<UserDO> eq = Wrappers.lambdaQuery(UserDO.class).eq(UserDO::getUsername, username);
        UserDO userDO = baseMapper.selectOne(eq);
        if(userDO==null){
            throw new ServiceException(UserErrorCodeEnum.USER_NULL);
        }
        UserRespDTO result = new UserRespDTO();
        BeanUtils.copyProperties(userDO,result);
        return result;
    }

    /**
     * 判断用户名是否存在
     * @param username
     * @return
     */
    @Override
    public Boolean hasUsername(String username) {
        return !userRegisterCachePenetrationBloomFilter.contains(username);
    }

    /**
     * 注册用户
     * @param requestParam
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void register(UserRegisterReqDTO requestParam) {
        if(!hasUsername(requestParam.getUsername())){
              throw new ClientException(USER_NAME_EXIST);
          }
        RLock lock = redissonClient.getLock(LOCK_USER_REGISTER_KEY + requestParam.getUsername());
        if(!lock.tryLock()){
            throw new ClientException(USER_NAME_EXIST);
        }
        try{
            int insert=baseMapper.insert(BeanUtil.toBean(requestParam,UserDO.class));
            if(insert<1){
                throw new ClientException(USER_SAVE_ERROR);
            }
            groupServiceimpl.saveGroup(requestParam.getUsername(),"默认分组");
            userRegisterCachePenetrationBloomFilter.add(requestParam.getUsername());
        }catch (DuplicateKeyException ex){
           throw new ClientException(USER_EXIST);
        }finally {
            lock.unlock();
        }
    }
}
