package com.zyh.shortlink.admin.remote.DTO.resp;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * author 邹宇航  @vision 1.0
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ShortLinkStatsAccessRecordRespDTO {

    private String uvType;

    private String browser;

    private String os;

    private String ip;

    private String network;

    private String device;

    private String locale;

    private String user;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;
}
