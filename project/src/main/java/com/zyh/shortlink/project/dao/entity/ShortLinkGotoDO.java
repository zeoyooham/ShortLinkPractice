package com.zyh.shortlink.project.dao.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * author 邹宇航  @vision 1.0
 */
@Builder
@Data
@TableName("t_link_goto")
@AllArgsConstructor
@NoArgsConstructor
public class ShortLinkGotoDO {

    private Long id;

    private String gid;

    private String fullShortUrl;
}
