package com.zyh.shortlink.admin.remote.DTO.resp;

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
public class ShortLinkStatsBrowserRespDTO {

    private Integer cnt;

    private String browser;

    private Double ratio;
}
