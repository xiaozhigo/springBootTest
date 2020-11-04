package com.example.springboottest.dto;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import com.alibaba.excel.annotation.write.style.ContentRowHeight;
import com.alibaba.excel.annotation.write.style.HeadRowHeight;
import lombok.Data;
@ContentRowHeight(20)
@HeadRowHeight(25)
@ColumnWidth(20)
@Data
public class ExcelDto {
    @ColumnWidth(15)
    @ExcelProperty(value = "用户Id",index = 0)
    private String userId;
    @ColumnWidth(15)
    @ExcelProperty(value = "用户名",index = 1)
    private String userName;
}
