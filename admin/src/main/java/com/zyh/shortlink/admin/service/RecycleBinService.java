package com.zyh.shortlink.admin.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zyh.shortlink.admin.convention.result.Result;
import com.zyh.shortlink.admin.remote.DTO.req.ShortLinkRecycleBinPageReqDTO;
import com.zyh.shortlink.admin.remote.DTO.resp.ShortLinkPageRespDTO;

/**
 *author 邹宇航  @vision 1.0
 */
public interface RecycleBinService {

    Result<Page<ShortLinkPageRespDTO>> pageRecycleBinShortLink(ShortLinkRecycleBinPageReqDTO requestParam);
}
