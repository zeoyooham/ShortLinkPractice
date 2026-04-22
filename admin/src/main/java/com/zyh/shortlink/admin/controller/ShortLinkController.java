package com.zyh.shortlink.admin.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zyh.shortlink.admin.convention.result.Result;
import com.zyh.shortlink.admin.convention.result.Results;
import com.zyh.shortlink.admin.dto.resp.ShortLinkBaseInfoRespDTO;
import com.zyh.shortlink.admin.dto.resp.ShortLinkBatchCreateRespDTO;
import com.zyh.shortlink.admin.remote.DTO.req.ShortLinkBatchCreateReqDTO;
import com.zyh.shortlink.admin.remote.DTO.req.ShortLinkCreateReqDTO;
import com.zyh.shortlink.admin.remote.DTO.req.ShortLinkPageReqDTO;
import com.zyh.shortlink.admin.remote.DTO.req.ShortLinkUpdateReqDTO;
import com.zyh.shortlink.admin.remote.DTO.resp.ShortLinkCreateRespDTO;
import com.zyh.shortlink.admin.remote.DTO.resp.ShortLinkPageRespDTO;
import com.zyh.shortlink.admin.remote.ShortLinkActualRemoteService;
import com.zyh.shortlink.admin.toolkit.EasyExcelWebUtil;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * author 邹宇航  @vision 1.0
 */
@RestController(value = "ShortLinkControllerByAdmin")
@AllArgsConstructor
public class ShortLinkController {

    private final ShortLinkActualRemoteService shortLinkActualRemoteService;

    @PutMapping("/api/short-link/admin/v1/create")
    public List<ShortLinkCreateRespDTO> createShortLink(@RequestBody ShortLinkCreateReqDTO requestParam){
        return shortLinkActualRemoteService.createShortLink(requestParam);
    }

    @PostMapping("/api/short-link/admin/v1/batch")
    public void batchCreateShortLink(@RequestBody ShortLinkBatchCreateReqDTO requestParam,HttpServletResponse response){
        Result<ShortLinkBatchCreateRespDTO> linkBatchCreateResult = shortLinkActualRemoteService.batchCreateShortLink(requestParam);
        if(linkBatchCreateResult.isSuccess()){
            List<ShortLinkBaseInfoRespDTO> baseLinkInfos = linkBatchCreateResult.getData().getBaseLinkInfos();
            EasyExcelWebUtil.write(response,"批量创建短链接-SaaS短链接系统",ShortLinkBaseInfoRespDTO.class,baseLinkInfos);
        }
    }

    @PostMapping("/api/short-link/admin/v1/update")
    public Result<Void> updateShortLink(@RequestBody ShortLinkUpdateReqDTO requestParam){
        shortLinkActualRemoteService.updateShortLink(requestParam);
        return Results.success();
    }

    @GetMapping("/api/short-link/admin/v1/page")
    public Result<Page<ShortLinkPageRespDTO>> pageShortLink(ShortLinkPageReqDTO requestParam){
        return shortLinkActualRemoteService.pageShortLink(requestParam.getGid(),requestParam.getOrderTag(), requestParam.getCurrent(),requestParam.getSize());
    }
}
