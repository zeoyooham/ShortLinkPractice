package com.zyh.shortlink.admin.dao.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.zyh.shortlink.admin.common.database.BaseDO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * author 邹宇航  @vision 1.0
 */
@Data
@TableName("t_group")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GroupDO extends BaseDO {

    private Long id;

    private String gid;

    private String name;

    private String username;

    private Integer sortOrder;
}
