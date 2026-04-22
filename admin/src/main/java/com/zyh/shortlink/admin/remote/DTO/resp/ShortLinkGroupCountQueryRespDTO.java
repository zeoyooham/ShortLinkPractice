package com.zyh.shortlink.admin.remote.DTO.resp;

import lombok.Data;

/**
 * author 邹宇航  @vision 1.0
 */
@Data
public class ShortLinkGroupCountQueryRespDTO {

    private String gid;

    private Integer shortLinkCount;
}
