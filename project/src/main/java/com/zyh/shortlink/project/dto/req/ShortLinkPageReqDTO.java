package com.zyh.shortlink.project.dto.req;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zyh.shortlink.project.dao.entity.ShortLinkDO;
import lombok.Data;

/**
 * author 邹宇航  @vision 1.0
 */
@Data
public class ShortLinkPageReqDTO extends Page<ShortLinkDO> {

    /**
     * 分组标识
     */
    private String gid;

    /**
     * 排序标识
     */
    private String orderTag;
}
