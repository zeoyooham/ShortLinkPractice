package com.zyh.shortlink.admin.remote.DTO.resp;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * author 邹宇航  @vision 1.0
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ShortLinkStatsLocaleCnRespDTO {

    private Integer cnt;

    private String locale;

    private Double ratio;
}
