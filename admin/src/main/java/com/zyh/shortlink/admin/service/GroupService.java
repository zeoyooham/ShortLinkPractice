package com.zyh.shortlink.admin.service;

import com.zyh.shortlink.admin.dto.req.ShortLinkGroupRespDTO;
import com.zyh.shortlink.admin.dto.req.ShortLinkGroupSortReqDTO;
import com.zyh.shortlink.admin.dto.req.ShortLinkGroupUpdateReqDTO;

import java.util.List;

/**
 * author 邹宇航  @vision 1.0
 */
public interface GroupService {

    void saveGroup(String group);

    void saveGroup(String username, String groupName);

    List<ShortLinkGroupRespDTO> listGroup();

    void updateGroup(ShortLinkGroupUpdateReqDTO requestParam);

    void updateGroup(String gid);

    void sortGroup(List<ShortLinkGroupSortReqDTO> requestParam);

    void deleteGroup(String gid);
}
