package com.zyh.shortlink.project.handler;

import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.zyh.shortlink.project.common.convention.result.Result;
import com.zyh.shortlink.project.dto.req.ShortLinkCreateReqDTO;
import com.zyh.shortlink.project.dto.resp.ShortLinkCreateRespDTO;

/**
 * author 邹宇航  @vision 1.0
 */
public class CustomBlockHandler {

    public static Result<ShortLinkCreateRespDTO> createShortLinkBlockHandlerMethod(ShortLinkCreateReqDTO requestParam, BlockException exception) {
        return new Result<ShortLinkCreateRespDTO>().setCode("B100000").setMessage("当前访问网站人数过多，请稍后再试...");
    }
}