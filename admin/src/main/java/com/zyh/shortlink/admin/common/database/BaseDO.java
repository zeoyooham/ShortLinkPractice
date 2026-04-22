package com.zyh.shortlink.admin.common.database;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

import java.util.Date;

/**
 * author 邹宇航  @vision 1.0
 */
@Data
public class BaseDO {

    @TableField(fill = FieldFill.INSERT)
    private Date createTime;

    @TableField(fill=FieldFill.UPDATE)
    private Date updateTime;

    @TableField(fill=FieldFill.INSERT)
    private Integer delFlag;
}
