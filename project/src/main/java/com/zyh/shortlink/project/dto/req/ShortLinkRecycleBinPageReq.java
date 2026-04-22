package com.zyh.shortlink.project.dto.req;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zyh.shortlink.project.dao.entity.ShortLinkDO;
import lombok.Data;

import java.util.List;

/**
 * author 邹宇航  @vision 1.0
 */
@Data
public class ShortLinkRecycleBinPageReq extends Page<ShortLinkDO> {

    /**
     * 分组标识
     */
    private List<String> gidList;
}
