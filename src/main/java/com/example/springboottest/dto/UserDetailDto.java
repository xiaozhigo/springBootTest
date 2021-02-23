package com.example.springboottest.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDetailDto {

    private String detailName;

    private String detailMessage;

    private Integer userId;
}
