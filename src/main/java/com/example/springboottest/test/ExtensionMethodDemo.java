package com.example.springboottest.test;

import lombok.Data;
import lombok.experimental.ExtensionMethod;
import java.util.Date;

import static com.example.springboottest.test.Extensions.format;

@Data
@ExtensionMethod({Extensions.class})
public class ExtensionMethodDemo {

    public static void main(String[] args) {
        Date date = new Date();
        String d = format(date);
        System.out.println(d);
    }
}
