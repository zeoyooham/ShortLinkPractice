package com.zyh.shortlink.project.dao.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.zyh.shortlink.project.dao.entity.ShortLinkDO;
import com.zyh.shortlink.project.dto.req.ShortLinkPageReqDTO;
import com.zyh.shortlink.project.dto.req.ShortLinkRecycleBinPageReq;
import org.apache.ibatis.annotations.Param;

/**
 * author 邹宇航  @vision 1.0
 */
public interface ShortLinkMapper extends BaseMapper<ShortLinkDO> {

    void incrementsStats(@Param("gid") String gid,
                         @Param("fullShortUrl") String fullShortUrl,
                         @Param("totalPv") Integer totalPv,
                         @Param("totalUv") Integer totalUv,
                         @Param("totalUip") Integer totalUip);

    IPage<ShortLinkDO> pageLink(ShortLinkPageReqDTO requestParam);

    IPage<ShortLinkDO> pageRecycleBinLink(ShortLinkRecycleBinPageReq requestParam);
}
