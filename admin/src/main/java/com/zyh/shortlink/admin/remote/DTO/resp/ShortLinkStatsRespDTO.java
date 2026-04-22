package com.zyh.shortlink.admin.remote.DTO.resp;

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
@AllArgsConstructor
@NoArgsConstructor
public class ShortLinkStatsRespDTO {

    private Integer pv;

    private Integer uv;

    private Integer uip;

    private List<ShortLinkStatsAccessDailyRespDTO> daily;

    private List<ShortLinkStatsLocaleCnRespDTO> localeCnStats;

    private List<Integer> hourStats;

    private List<ShortLinkStatsTopIpRespDTO> topIpStats;

    private List<Integer> weekdayStats;

    private List<ShortLinkStatsBrowserRespDTO> browserStats;

    private List<ShortLinkStatsOsRespDTO> osStats;

    private List<ShortLinkStatsUvRespDTO> uvTypeStats;

    private List<ShortLinkStatsDeviceRespDTO> deviceStats;

    private List<ShortLinkStatsNetworkRespDTO> networkStats;
}
