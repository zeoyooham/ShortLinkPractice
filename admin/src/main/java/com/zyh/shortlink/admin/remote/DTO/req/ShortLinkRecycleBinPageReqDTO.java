package com.zyh.shortlink.admin.remote.DTO.req;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.Data;

import java.util.List;

/**
 * author 邹宇航  @vision 1.0
 */
@Data
public class ShortLinkRecycleBinPageReqDTO extends Page {

    private List<String> gidList;
}
