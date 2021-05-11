package com.example.springboottest.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import reactor.core.publisher.Mono;

@Controller
public class WebFluxController {

    @RequestMapping(value = "webFluxTest1",method = RequestMethod.GET)
    @ResponseBody
    public Mono<String> test(){
        return Mono.just("哈哈");
    }
}
