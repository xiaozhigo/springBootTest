package com.example.springboottest.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDetailDto implements Serializable {

    private String detailName;

    private String detailMessage;

    private Integer userId;
}
