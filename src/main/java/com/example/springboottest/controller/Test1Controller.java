package com.example.springboottest.controller;

import com.example.springboottest.config.Person;
import com.example.springboottest.dto.UserDetailDto;
import com.example.springboottest.dto.UserDto;
import com.example.springboottest.service.Test1Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class Test1Controller {

    @Autowired
    private Person person;
    @Autowired
    private Test1Service test1Service;

    @RequestMapping("/servletTest.do")
    public String servletTest(){
         return "index";
    }

    @RequestMapping("/getPerson")
    @ResponseBody
    public String getPerson(){
        System.out.println(person.getMyName());
        return "index";
    }

    @RequestMapping("/insertUserDetail")
    public void insertUserDetail(@RequestBody UserDto userDto){
        test1Service.insertUserDetail(userDto);
    }
}
