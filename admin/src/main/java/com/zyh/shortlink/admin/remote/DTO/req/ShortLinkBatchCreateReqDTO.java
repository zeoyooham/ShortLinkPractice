package com.zyh.shortlink.admin.remote.DTO.req;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * author 邹宇航  @vision 1.0
 */
@Data
public class ShortLinkBatchCreateReqDTO {

    private List<String> originUrls;

    private List<String> describes;

    private String gid;

    private Integer createType;

    private Integer validDateType;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date validDate;
}
