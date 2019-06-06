package com.example.service;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.*;
import entity.Book;
/**
 * 测试Feign service 接口类
 * 通过@FeignClient注解来指定服务名进而绑定服务
 * 就是服务提供者的服务名
 */
//hello-service大小写无所谓
@FeignClient(value = "hello-service",fallback = HelloServiceFallback.class)
public interface FeignService {

    /**
     * Feign 是基于接口方式调用服务提供者方法
     * 根据服务提供者的服务名(如 hello-service) 在注册中心获取对应的服务,
     * 在根据 @RequestMapping("/hello") 中的映射路径找到对应的方法,
     * 所以,使用Feign 的关键是@FeignClient("hello-service")中的服务提供者名称 和 映射的路径
     * 其他的方法名可以随便命名
     */
    /**
     * 无参
     */
    @RequestMapping("/hello")
    String hello();

    /**
     * ，在SpringMVC中，@RequestParam和@RequestHeader注解，如果我们不指定value，
     * 则默认采用参数的名字作为其value，但是在Feign中，这个value必须明确指定，否则会报错。
     */
    /**
     * 一个参数,get请求
     */
    @RequestMapping(value = "/hello1", method = RequestMethod.GET)
    String hello(@RequestParam("name") String name);

    /**
     * 多个参数,get请求
     */
    @RequestMapping(value = "/hello2", method = RequestMethod.GET)
    Book hello(@RequestHeader("name") String name, @RequestHeader("author") String author, @RequestHeader("price") Integer price);

    /**
     * 对象,post请求
     */
    @RequestMapping(value = "/hello3", method = RequestMethod.POST)
    String hello(@RequestBody Book book);

}
