package com.example.controller;

import com.example.service.HelloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * 服务消费 Controller
 */
@RestController
public class RibbonConsumerController {
    @Autowired
   private HelloService helloService;

    @RequestMapping(value = "/ribbon-consumer",method = RequestMethod.GET)
    public String helloController() {
         String hello = helloService.hello();
         return hello;
    }

    @RequestMapping(value = "/test1",method = RequestMethod.GET)
    public String test1() {
        String hello = helloService.hello();
        return "这是测试test1";
    }

    @RequestMapping(value = "/test2",method = RequestMethod.GET)
    public String test2() {
        String error = helloService.error();
        return error;
    }
}
