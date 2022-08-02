package com.example.springboottest.dto;

import lombok.Data;
import org.springframework.data.annotation.Id;

import javax.persistence.Column;
import javax.persistence.Table;

@Data
@Table(name = "td_user")
public class User {

    @Id
    @Column(name = "user_id")
    private Integer userId;
    @Column(name = "user_name")
    private String userName;
}
