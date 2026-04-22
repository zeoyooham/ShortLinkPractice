package com.zyh.shortlink.admin.remote.DTO.req;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.Data;

import java.util.Date;

/**
 * author 邹宇航  @vision 1.0
 */
@Data
public class ShortLinkStatsGroupAccessRecordReqDTO extends Page {

    private String gid;

    private Date startDate;

    private Date endDate;

}
