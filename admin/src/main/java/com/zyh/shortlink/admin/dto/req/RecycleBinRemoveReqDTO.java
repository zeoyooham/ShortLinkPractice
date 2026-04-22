package com.zyh.shortlink.admin.dto.req;

import lombok.Data;

/**
 * author 邹宇航  @vision 1.0
 */
@Data
public class RecycleBinRemoveReqDTO {

    private String gid;

    private String fullShortLink;
}
