package com.zyh.shortlink.admin.dto.resp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * author 邹宇航  @vision 1.0
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ShortLinkBatchCreateRespDTO {

    private Integer total;

    private List<ShortLinkBaseInfoRespDTO> baseLinkInfos;
}
