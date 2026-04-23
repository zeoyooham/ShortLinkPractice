package com.zyh.shortlink.project.controller;

import com.zyh.shortlink.project.common.convention.result.Result;
import com.zyh.shortlink.project.common.convention.result.Results;
import com.zyh.shortlink.project.dto.req.RecycleBinRecoverReqDTO;
import com.zyh.shortlink.project.dto.req.RecycleBinSaveReqDTO;
import com.zyh.shortlink.project.service.RecycleBinService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

/**
 * author 邹宇航  @vision 1.0
 */
@RestController
@AllArgsConstructor
public class RecycleBinController {

    private final RecycleBinService recycleBinService;

    @PostMapping("/api/short-link/v1/recycle-bin/save")
    public Result<Void> saveRecycleBin(@RequestBody RecycleBinSaveReqDTO requestParam){
        recycleBinService.saveRecycleBin(requestParam);
        return Results.success();
    }

    @PostMapping("/api/short-link/v1/recycle-bin/recover")
    public Result<Void> recoverRecycleBin(@RequestBody RecycleBinRecoverReqDTO requestParam){
        recycleBinService.recoverRecycleBin(requestParam);
        return Results.success();
    }
}
