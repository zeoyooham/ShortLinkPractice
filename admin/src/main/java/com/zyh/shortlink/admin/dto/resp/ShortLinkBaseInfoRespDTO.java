package com.zyh.shortlink.admin.dto.resp;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * author 邹宇航  @vision 1.0
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ShortLinkBaseInfoRespDTO {

    @ExcelProperty(value = "标题")
    @ColumnWidth(40)
    private String describe;

    @ExcelProperty(value = "短链接")
    @ColumnWidth(40)
    private String fullShortUrl;

    @ExcelProperty(value = "原始链接")
    @ColumnWidth(80)
    private String originUrl;
}
