package com.example.springboottest.controller;

import com.example.springboottest.config.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class Test1Controller {

    @Autowired
    private Person person;

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
}
