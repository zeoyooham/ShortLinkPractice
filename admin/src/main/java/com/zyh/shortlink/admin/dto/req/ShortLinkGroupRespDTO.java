package com.zyh.shortlink.admin.dto.req;

import lombok.Data;

/**
 * author 邹宇航  @vision 1.0
 */
@Data
public class ShortLinkGroupRespDTO {

    private String gid;

    private String name;

    private Integer sortOrder;

    private Integer shortLinkCount;
}
