package com.zyh.shortlink.admin.dto.req;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

/**
 * author 邹宇航  @vision 1.0
 */
@Data
public class ShortLinkStatsReqDTO {

    private String fullShortLink;

    private String gid;

    private Date startDate;

    private Date endDate;

    private Integer enableStatus;
}
