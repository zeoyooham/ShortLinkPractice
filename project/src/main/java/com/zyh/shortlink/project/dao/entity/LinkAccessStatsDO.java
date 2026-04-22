package com.zyh.shortlink.project.dao.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.zyh.shortlink.project.common.database.BaseDO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * author 邹宇航  @vision 1.0
 */
@Data
@Builder
@TableName("t_link_access_stats")
@AllArgsConstructor
@NoArgsConstructor
public class LinkAccessStatsDO extends BaseDO {

    private Long id;

    private String fullShortUrl;

    private Date date;

    private Integer pv;

    private Integer uv;

    private Integer uip;

    private Integer hour;

    private Integer weekday;

}
