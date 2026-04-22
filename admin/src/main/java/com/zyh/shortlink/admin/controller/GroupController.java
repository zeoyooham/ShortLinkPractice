package com.zyh.shortlink.admin.controller;

import com.zyh.shortlink.admin.convention.result.Result;
import com.zyh.shortlink.admin.convention.result.Results;
import com.zyh.shortlink.admin.dto.req.ShortLinkGroupRespDTO;
import com.zyh.shortlink.admin.dto.req.ShortLinkGroupSaveReqDTO;
import com.zyh.shortlink.admin.dto.req.ShortLinkGroupSortReqDTO;
import com.zyh.shortlink.admin.dto.req.ShortLinkGroupUpdateReqDTO;
import com.zyh.shortlink.admin.service.GroupService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * author 邹宇航  @vision 1.0
 */
@RestController
@AllArgsConstructor
public class GroupController {

    private final GroupService groupService;

    @PostMapping("/api/short-link/admin/v1/group")
    public Result<Void>  saveGroup(@RequestBody ShortLinkGroupSaveReqDTO requestParam){
        groupService.saveGroup(requestParam.getName());
        return Results.success();
    }

    @GetMapping("/api/short-link/admin/v1/group")
    public Result<List<ShortLinkGroupRespDTO>> listGroup(){
        return Results.success(groupService.listGroup());
    }

    @PutMapping("/api/short-link/admin/v1/group")
    public Result<Void> updateGroup(@RequestBody ShortLinkGroupUpdateReqDTO requestParam){
        groupService.updateGroup(requestParam);
        return Results.success();
    }

    @DeleteMapping("/api/short-link/admin/v1/group")
    public Result<Void> updateGroup(@RequestParam String gid){
        groupService.updateGroup(gid);
        return Results.success();
    }

    @PostMapping("/api/short-link/admin/v1/sort")
    public Result<Void> sortGroup(@RequestBody List<ShortLinkGroupSortReqDTO> requestParam){
        groupService.sortGroup(requestParam);
        return Results.success();
    }

    @DeleteMapping("/api/short-link/admin/v1/delete")
    public Result<Void> deleteGroup(@PathVariable String gid){
        groupService.deleteGroup(gid);
        return Results.success();
    }

}
