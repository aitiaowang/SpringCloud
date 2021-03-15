package com.example.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
* @Description:    用于测试Zuul的本地跳转
* @Author:         sxk
* @CreateDate:     2019/7/16 10:40
* @Version:        1.0
*/
@RestController
public class HelloController {

    @RequestMapping("/local")
    public String hello() {
        return "hello api gateway";
    }
}
