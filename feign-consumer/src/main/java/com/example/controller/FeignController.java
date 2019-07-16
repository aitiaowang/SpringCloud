package com.example.controller;

import com.example.service.FeignService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import entity.Book;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 * Feign Controller
 */
@RestController
public class FeignController {

    @Autowired
    private FeignService feignService;

    /**
     * 测试Feign负载均衡
     * @author     sxk
     * @date        2019/7/16 15:59
     */
    @RequestMapping("/testFeignLoadBalance")
    public void testFeignLoadBalance(){
        feignService.testFeignLoadBalance();
    }

    /**
     * 用于测试Feign 调用服务提供者的方法(服务提供者方法参数为: 无参)
     *
     * @return
     */
    @RequestMapping("/hello")
    public String feignHello() {
        return feignService.hello();
    }


    /**
     * 用于测试Feign 调用服务提供者 (服务提供者方法参数为: 一个参数,get请求)
     */
    @RequestMapping("/hello1")
    public String hello1() {
        return feignService.hello("张三");
    }

    /**
     * 用于测试Feign 调用服务提供者 (服务提供者方法参数为: 多个参数,get请求)
     */
    @RequestMapping(value = "/hello2")
    public Book hello2() throws UnsupportedEncodingException {
        Book book = feignService.hello(URLEncoder.encode("三国演义", "UTF-8"), URLEncoder.encode("罗贯中", "UTF-8"), 33);
        System.out.println(book);
        return book;
    }

    /**
     * 用于测试Feign 调用服务提供者 (服务提供者方法参数为: 对象,post请求)
     */
    @RequestMapping("/hello3")
    public String hello3() {
        Book book = new Book();
        book.setName("红楼梦");
        book.setPrice(44);
        book.setAuthor("曹雪芹");
        return feignService.hello(book);
    }

}
