package com.zyh.shortlink.admin.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zyh.shortlink.admin.common.biz.user.UserContext;
import com.zyh.shortlink.admin.convention.exception.ClientException;
import com.zyh.shortlink.admin.convention.result.Result;
import com.zyh.shortlink.admin.dao.entity.GroupDO;
import com.zyh.shortlink.admin.dao.entity.GroupUniqueDO;
import com.zyh.shortlink.admin.dao.mapper.GroupMapper;
import com.zyh.shortlink.admin.dao.mapper.GroupUniqueMapper;
import com.zyh.shortlink.admin.dto.req.ShortLinkGroupRespDTO;
import com.zyh.shortlink.admin.dto.req.ShortLinkGroupSortReqDTO;
import com.zyh.shortlink.admin.dto.req.ShortLinkGroupUpdateReqDTO;
import com.zyh.shortlink.admin.remote.DTO.resp.ShortLinkGroupCountQueryRespDTO;
import com.zyh.shortlink.admin.remote.ShortLinkActualRemoteService;
import com.zyh.shortlink.admin.service.GroupService;
import com.zyh.shortlink.admin.toolkit.RandomGenerator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RBloomFilter;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import javax.swing.*;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import static com.zyh.shortlink.admin.common.constant.RedisCacheConstant.LOCK_GROUP_CREATE_KEY;

/**
 * author 邹宇航  @vision 1.0
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class GroupServiceImpl extends ServiceImpl<GroupMapper, GroupDO> implements GroupService {

    @Value("10")
    private Integer groupMaxNum;
    
    private final RedissonClient redissonClient;
    private final RBloomFilter<String> gidRegisterCachePenetrationBloomFilter;
    private final GroupUniqueMapper groupUniqueMapper;
    private final ShortLinkActualRemoteService shortLinkActualRemoteService;

    @Override
    public void saveGroup(String groupName) {
         saveGroup(UserContext.getUsername());
    }

    @Override
    public void saveGroup(String username, String groupName) {
        RLock lock = redissonClient.getLock(String.format(LOCK_GROUP_CREATE_KEY, username));
        lock.lock();
        try{
            LambdaQueryWrapper<GroupDO> queryMapper = Wrappers.lambdaQuery(GroupDO.class)
                    .eq(GroupDO::getUsername, username)
                    .eq(GroupDO::getDelFlag, 0);
            List<GroupDO> groupDOList = baseMapper.selectList(queryMapper);
            if(CollUtil.isNotEmpty(groupDOList)&&groupDOList.size()==groupMaxNum){
                throw new ClientException(String.format("已超出最大分组数：%d", groupMaxNum));
            }
            int retryCounts=0;
            int maxRetries=10;
            String gid=null;
            while(retryCounts<maxRetries){
                gid=saveGroupUniqueReturnGid();
                if(StrUtil.isNotEmpty(gid)){
                    GroupDO groupDO = GroupDO.builder()
                            .gid(gid)
                            .name(groupName)
                            .sortOrder(0)
                            .username(username)
                            .build();
                    baseMapper.insert(groupDO);
                    gidRegisterCachePenetrationBloomFilter.add(gid);
                    break;
                }
                retryCounts++;
            }
            if(StrUtil.isEmpty(gid)){
                throw new ClientException("生成分组标识频繁");
            }
        } finally {
            lock.unlock();
        }
    }

    @Override
    public List<ShortLinkGroupRespDTO> listGroup() {
        LambdaQueryWrapper<GroupDO> queryMapper = Wrappers.lambdaQuery(GroupDO.class)
                .eq(GroupDO::getDelFlag, 0)
                .eq(GroupDO::getUsername, UserContext.getUsername())
                .orderByDesc(GroupDO::getSortOrder, GroupDO::getUpdateTime);
        List<GroupDO> groupDOList = baseMapper.selectList(queryMapper);
        Result<List<ShortLinkGroupCountQueryRespDTO>> listResult = shortLinkActualRemoteService
                .listGroupShortLinkCount(groupDOList.stream().map(GroupDO::getGid).toList());
        List<ShortLinkGroupRespDTO> shortLinkGroupRespDTOList = BeanUtil.copyToList(groupDOList, ShortLinkGroupRespDTO.class);
        shortLinkGroupRespDTOList.forEach(each -> {
            Optional<ShortLinkGroupCountQueryRespDTO> first = listResult.getData().stream()
                    .filter(item -> Objects.equals(item.getGid(), each.getGid()))
                    .findFirst();
            first.ifPresent(item -> each.setShortLinkCount(first.get().getShortLinkCount()));
        });
        return shortLinkGroupRespDTOList;
    }

    @Override
    public void updateGroup(ShortLinkGroupUpdateReqDTO requestParam) {
        LambdaQueryWrapper<GroupDO> eq = Wrappers.lambdaQuery(GroupDO.class)
                .eq(GroupDO::getName, UserContext.getUsername())
                .eq(GroupDO::getGid, requestParam.getGid())
                .eq(GroupDO::getDelFlag, 0);
        GroupDO groupDO = new GroupDO();
        groupDO.setName(requestParam.getName());
        baseMapper.update(groupDO,eq);
    }

    @Override
    public void updateGroup(String gid) {
        LambdaQueryWrapper<GroupDO> eq = Wrappers.lambdaQuery(GroupDO.class)
                .eq(GroupDO::getName, UserContext.getUsername())
                .eq(GroupDO::getDelFlag, 0)
                .eq(GroupDO::getGid, gid);
        GroupDO groupDO = new GroupDO();
        groupDO.setDelFlag(1);
        baseMapper.update(groupDO,eq);
    }

    @Override
    public void sortGroup(List<ShortLinkGroupSortReqDTO> requestParam) {
        requestParam.forEach(each -> {
            GroupDO groupDO = GroupDO.builder().sortOrder(each.getSortOrder()).build();
            LambdaQueryWrapper<GroupDO> eq = Wrappers.lambdaQuery(GroupDO.class)
                    .eq(GroupDO::getGid, each.getGid())
                    .eq(GroupDO::getUsername, UserContext.getUsername())
                    .eq(GroupDO::getDelFlag, 0);
            baseMapper.update(groupDO,eq);
        });
    }

    @Override
    public void deleteGroup(String gid) {
        LambdaQueryWrapper<GroupDO> eq = Wrappers.lambdaQuery(GroupDO.class)
                .eq(GroupDO::getUsername, UserContext.getUsername())
                .eq(GroupDO::getGid, gid)
                .eq(GroupDO::getDelFlag, 0);
        GroupDO group = new GroupDO();
        group.setDelFlag(1);
        baseMapper.update(group,eq);
    }


    private String saveGroupUniqueReturnGid(){
        String gid= RandomGenerator.generateRandom();
        if(gidRegisterCachePenetrationBloomFilter.contains(gid)){
            return null;
        }
        GroupUniqueDO groupUniqueDO = GroupUniqueDO.builder().gid(gid).build();
        try{
            groupUniqueMapper.insert(groupUniqueDO);
        }catch (DuplicateKeyException ex){
            return null;
        }
        return gid;
    }
}
