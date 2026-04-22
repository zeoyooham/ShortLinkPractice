package com.zyh.shortlink.admin.remote.DTO.req;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

/**
 * author 邹宇航  @vision 1.0
 */
@Data
public class ShortLinkUpdateReqDTO {

    private String originUrl;

    private String fullShortUrl;

    private String originGid;

    private String gid;

    private Integer validDateType;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date validDate;

    private String describe;
}
