package com.zyh.shortlink.admin.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zyh.shortlink.admin.convention.result.Result;
import com.zyh.shortlink.admin.dto.req.ShortLinkGroupRespDTO;
import com.zyh.shortlink.admin.dto.req.ShortLinkStatsReqDTO;
import com.zyh.shortlink.admin.remote.DTO.req.ShortLinkGroupStatsReqDTO;
import com.zyh.shortlink.admin.remote.DTO.req.ShortLinkStatsAccessRecordReqDTO;
import com.zyh.shortlink.admin.remote.DTO.req.ShortLinkStatsGroupAccessRecordReqDTO;
import com.zyh.shortlink.admin.remote.DTO.resp.ShortLinkStatsAccessRecordRespDTO;
import com.zyh.shortlink.admin.remote.DTO.resp.ShortLinkStatsRespDTO;
import com.zyh.shortlink.admin.remote.ShortLinkActualRemoteService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * author 邹宇航  @vision 1.0
 */
@RestController(value = "ShortLinkStatsControllerByAdmin")
@AllArgsConstructor
public class ShortLinkStatsController {

    private final ShortLinkActualRemoteService shortLinkActualRemoteService;

    @GetMapping("/api/short-link/admin/v1/stats")
    public Result<ShortLinkStatsRespDTO> shortLinkStats(ShortLinkStatsReqDTO requestParam){
         return shortLinkActualRemoteService.oneShortLinkStats(requestParam.getGid(),
                 requestParam.getFullShortLink(),
                 requestParam.getStartDate(),
                 requestParam.getEndDate(),
                 requestParam.getEnableStatus());
    }

    @GetMapping("/api/short-link/admin/v1/group")
    public Result<ShortLinkStatsRespDTO> groupShortLinkStats(ShortLinkGroupStatsReqDTO requestParam){
        return shortLinkActualRemoteService.groupShortLinkStats(requestParam.getGid(),
                requestParam.getStartDate(),
                requestParam.getEndDate());
    }

    @GetMapping("/api/short-link/admin/v1/access-record")
    public Result<Page<ShortLinkStatsAccessRecordRespDTO>> shortLinkStatsAccessRecord(ShortLinkStatsAccessRecordReqDTO requestParam) {
        return shortLinkActualRemoteService.shortLinkStatsAccessRecord(
                requestParam.getFullShortUrl(),
                requestParam.getGid(),
                requestParam.getStartDate(),
                requestParam.getEndDate(),
                requestParam.getCurrent(),
                requestParam.getSize());
    }

    @GetMapping("/api/short-link/admin/v1/access-record/group")
    public Result<Page<ShortLinkStatsAccessRecordRespDTO>> groupShortLinkStatsAccessRecord(ShortLinkStatsGroupAccessRecordReqDTO requestParam){
        return shortLinkActualRemoteService.groupShortLinkStatsAccessRecord(
                requestParam.getGid(),
                requestParam.getStartDate(),
                requestParam.getEndDate(),
                requestParam.getCurrent(),
                requestParam.getSize());
    }
}
