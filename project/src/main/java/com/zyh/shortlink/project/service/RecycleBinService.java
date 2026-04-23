package com.zyh.shortlink.project.service;

import com.zyh.shortlink.project.dto.req.RecycleBinRecoverReqDTO;
import com.zyh.shortlink.project.dto.req.RecycleBinSaveReqDTO;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * author 邹宇航  @vision 1.0
 */
public interface RecycleBinService {

    void saveRecycleBin(RecycleBinSaveReqDTO requestParam);

    void recoverRecycleBin(RecycleBinRecoverReqDTO requestParam);
}
