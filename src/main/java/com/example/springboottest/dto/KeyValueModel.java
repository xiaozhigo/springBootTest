package com.example.springboottest.dto;

import lombok.Data;

@Data
public class KeyValueModel<T> {

       private String key;

       private T vaule;
}
