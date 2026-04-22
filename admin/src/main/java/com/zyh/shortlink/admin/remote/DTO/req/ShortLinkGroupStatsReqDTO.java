package com.zyh.shortlink.admin.remote.DTO.req;

import lombok.Data;

import java.util.Date;

/**
 * author 邹宇航  @vision 1.0
 */
@Data
public class ShortLinkGroupStatsReqDTO {

    private String gid;

    private Date startDate;

    private Date endDate;
}
