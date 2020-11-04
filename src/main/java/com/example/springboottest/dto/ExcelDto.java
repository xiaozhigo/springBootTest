package com.example.springboottest.dto;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

@Data
public class ExcelDto {

    @ExcelProperty(value = "用户Id",index = 0)
    private String userId;
    @ExcelProperty(value = "用户名",index = 1)
    private String userName;
}
