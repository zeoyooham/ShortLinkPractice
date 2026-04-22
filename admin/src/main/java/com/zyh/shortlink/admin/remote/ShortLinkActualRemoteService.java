package com.zyh.shortlink.admin.remote;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zyh.shortlink.admin.config.OpenFeignConfiguration;
import com.zyh.shortlink.admin.convention.result.Result;
import com.zyh.shortlink.admin.dto.req.RecycleBinRecoverReqDTO;
import com.zyh.shortlink.admin.dto.req.RecycleBinRemoveReqDTO;
import com.zyh.shortlink.admin.dto.req.RecycleBinSaveReqDTO;
import com.zyh.shortlink.admin.dto.resp.ShortLinkBatchCreateRespDTO;
import com.zyh.shortlink.admin.remote.DTO.req.*;
import com.zyh.shortlink.admin.remote.DTO.resp.*;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

/**
 * author 邹宇航  @vision 1.0
 */
@FeignClient(
        value = "short-link:project",
        url = "${short-link.project.url}",
        configuration = OpenFeignConfiguration.class
)
public interface ShortLinkActualRemoteService {

    @GetMapping("/api/short-link/v1/count")
    Result<List<ShortLinkGroupCountQueryRespDTO>> listGroupShortLinkCount(@RequestParam("requestParam") List<String> requestParam);

    @PutMapping("/api/short-link/admin/v1/create")
    List<ShortLinkCreateRespDTO> createShortLink(@RequestBody ShortLinkCreateReqDTO requestParam);

    @PostMapping("/api/short-link/admin/v1/batch")
    Result<ShortLinkBatchCreateRespDTO> batchCreateShortLink(@RequestBody ShortLinkBatchCreateReqDTO requestParam);

    @PostMapping("/api/short-link/admin/v1/update")
    void updateShortLink(@RequestBody ShortLinkUpdateReqDTO requestParam);

    @GetMapping("/api/short-link/admin/v1/page")
    Result<Page<ShortLinkPageRespDTO>> pageShortLink(@RequestParam("gid")String gid,
                                                     @RequestParam("orderTag") Integer orderTag,
                                                     @RequestParam("current")Long current,
                                                     @RequestParam("size")Long size);

    @GetMapping("/api/short-link/admin/v1/stats")
    Result<ShortLinkStatsRespDTO> oneShortLinkStats(@RequestParam("gid")String gid,
                                                    @RequestParam("fullShortLink")String fullShortLink,
                                                    @RequestParam("startDate") Date startDate,
                                                    @RequestParam("endDate")Date endDate,
                                                    @RequestParam("enableStatus")Integer enableStatus);

    @GetMapping("/api/short-link/admin/v1/group")
    Result<ShortLinkStatsRespDTO> groupShortLinkStats(@RequestParam("gid") String gid,
                                                      @RequestParam("startDate")Date startDate,
                                                      @RequestParam("endDate")Date endDate);

    @GetMapping("/api/short-link/admin/v1/access-record")
    Result<Page<ShortLinkStatsAccessRecordRespDTO>> shortLinkStatsAccessRecord(@RequestParam("fullShortUrl")String fullShortUrl,
                                                                               @RequestParam("gid") String gid,
                                                                               @RequestParam("startDate") Date startDate,
                                                                               @RequestParam("endDate") Date endDate,
                                                                               @RequestParam("current")Long current,
                                                                               @RequestParam("size")Long size);

    @GetMapping("/api/short-link/admin/v1/access-record/group")
    Result<Page<ShortLinkStatsAccessRecordRespDTO>> groupShortLinkStatsAccessRecord(@RequestParam("gid") String gid,
                                                                                    @RequestParam("startDate") Date startDate,
                                                                                    @RequestParam("endDate") Date endDate,
                                                                                    @RequestParam("current")Long current,
                                                                                    @RequestParam("size")Long size);

    @GetMapping("/api/short-link/admin/v1/title")
    Result<String> getTitleByUrl(@RequestParam("url") String url);

    @PostMapping("/api/short-link/admin/v1/recycle-bin/save")
    Result<Void> saveRecycleBin(@RequestBody RecycleBinSaveReqDTO requestParam);

    @GetMapping("/api/short-link/admin/v1/recycle-bin/page")
    Result<Page<ShortLinkPageRespDTO>> pageRecycleBinShortLink(@RequestParam("gidList")List<String> gidList,
                                                     @RequestParam("current")Long current,
                                                     @RequestParam("size")Long size);

    @PostMapping("/api/short-link/admin/v1/recycle-bin/recover")
    Result<Void> recoverRecycleBin(@RequestBody RecycleBinRecoverReqDTO requestParam);

    @PostMapping("/api/short-link/admin/v1/recycle-bin/remove")
    void removeRecycleBin(@RequestBody RecycleBinRemoveReqDTO requestParam);
}
