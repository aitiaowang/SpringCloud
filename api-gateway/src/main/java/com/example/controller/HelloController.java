package com.example.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 用于测试Zuul的本地跳转
 */
@RestController
public class HelloController {

    @RequestMapping("/local")
    public String hello() {
        return "hello api gateway";
    }
}
