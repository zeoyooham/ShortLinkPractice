package com.zyh.shortlink.admin.remote.DTO.resp;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

/**
 * author 邹宇航  @vision 1.0
 */
@Data
public class ShortLinkPageRespDTO {

    private Integer id;

    private String domain;

    private String shortUrl;

    private String fullShortUrl;

    private String gid;

    private Integer validDateType;

    private Integer enableStatus;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date ValidDate;

    @JsonFormat(pattern = "yy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date createTime;

    private String describe;

    private String favicon;

    private Integer todayPv;

    private Integer totalPv;

    private Integer todayUv;

    private Integer totalUv;

    private Integer todayUip;

    private Integer totalUip;
}
