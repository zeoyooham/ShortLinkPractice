package com.zyh.shortlink.admin.dao.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * author 邹宇航  @vision 1.0
 */
@Data
@TableName("t_group_unique")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GroupUniqueDO  {

    private Long id;

    private String gid;
}
