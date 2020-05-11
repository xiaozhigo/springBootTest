package com.example.springboottest.test;

import org.junit.Test;
import org.springframework.util.ResourceUtils;

import java.io.*;

public class IOTest {

    @Test
    public void test1() throws Exception {
        File file = ResourceUtils.getFile("classpath:新建文本文档.txt");
        FileReader fileReader = new FileReader(file);
        BufferedReader reader = new BufferedReader(fileReader);
        String line;
        while((line = reader.readLine()) != null){
            System.out.println(line);
         }
        reader.close();
        fileReader.close();
    }
}
