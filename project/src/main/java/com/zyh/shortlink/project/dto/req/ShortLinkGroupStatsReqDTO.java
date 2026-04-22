package com.zyh.shortlink.project.dto.req;

import lombok.Data;

/**
 * author 邹宇航  @vision 1.0
 */
@Data
public class ShortLinkGroupStatsReqDTO {

    /**
     * 分组标识
     */
    private String gid;

    /**
     * 开始日期
     */
    private String startDate;

    /**
     * 结束日期
     */
    private String endDate;
}
