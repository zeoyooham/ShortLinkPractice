package com.zyh.shortlink.admin.controller;

import com.zyh.shortlink.admin.convention.result.Result;
import com.zyh.shortlink.admin.convention.result.Results;
import com.zyh.shortlink.admin.remote.ShortLinkActualRemoteService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * author 邹宇航  @vision 1.0
 */
@RestController(value = "UrlTitleControllerByAdmin")
@RequiredArgsConstructor
public class UrlTitleController {

    private final ShortLinkActualRemoteService shortLinkActualRemoteService;

    @GetMapping("/api/short-link/admin/v1/title")
    public Result<String> getTitleByUrl(@RequestParam String url){
        return shortLinkActualRemoteService.getTitleByUrl(url);
    }
}
