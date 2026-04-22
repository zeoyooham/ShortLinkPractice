package com.zyh.shortlink.admin.remote.DTO.req;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.Data;

/**
 * author 邹宇航  @vision 1.0
 */
@Data
public class ShortLinkPageReqDTO extends Page {

    private String gid;

    private Integer orderTag;
}
