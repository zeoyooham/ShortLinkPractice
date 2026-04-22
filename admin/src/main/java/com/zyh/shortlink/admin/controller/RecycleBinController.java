package com.zyh.shortlink.admin.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zyh.shortlink.admin.convention.result.Result;
import com.zyh.shortlink.admin.convention.result.Results;
import com.zyh.shortlink.admin.dto.req.RecycleBinRecoverReqDTO;
import com.zyh.shortlink.admin.dto.req.RecycleBinRemoveReqDTO;
import com.zyh.shortlink.admin.dto.req.RecycleBinSaveReqDTO;
import com.zyh.shortlink.admin.remote.DTO.req.ShortLinkRecycleBinPageReqDTO;
import com.zyh.shortlink.admin.remote.DTO.resp.ShortLinkPageRespDTO;
import com.zyh.shortlink.admin.remote.ShortLinkActualRemoteService;
import com.zyh.shortlink.admin.service.RecycleBinService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

/**
 * author 邹宇航  @vision 1.0
 */
@RestController(value = "RecycleBinControllerByAdmin")
@RequiredArgsConstructor
public class RecycleBinController {

    private final ShortLinkActualRemoteService shortLinkActualRemoteService;
    private final RecycleBinService recycleBinService;

    @PostMapping("/api/short-link/admin/v1/recycle-bin/save")
    public Result<Void> saveRecycleBin(@RequestBody RecycleBinSaveReqDTO requestParam){
        shortLinkActualRemoteService.saveRecycleBin(requestParam);
        return Results.success();
    }

    @GetMapping("/api/short-link/admin/v1/recycle-bin/page")
    public Result<Page<ShortLinkPageRespDTO>> pageShortLink(ShortLinkRecycleBinPageReqDTO requestParam){
        return recycleBinService.pageRecycleBinShortLink(requestParam);
    }

    @PostMapping("/api/short-link/admin/v1/recycle-bin/recover")
    public Result<Void> recoverRecycleBin(RecycleBinRecoverReqDTO requestParam){
        shortLinkActualRemoteService.recoverRecycleBin(requestParam);
        return Results.success();
    }

    @PostMapping("/api/short-link/admin/v1/recycle-bin/remove")
    public Result<Void> removeRecycleBin(@RequestBody RecycleBinRemoveReqDTO requestParam){
        shortLinkActualRemoteService.removeRecycleBin(requestParam);
        return Results.success();
    }

}
