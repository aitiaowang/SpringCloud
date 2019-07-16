package com.example.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.*;
import entity.Book;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.List;

@RestController
@Slf4j
public class HelloController {
    @Autowired
    private DiscoveryClient client;

    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String index() {
        List<ServiceInstance> instances = client.getInstances("hello-service");
        for (int i = 0; i < instances.size(); i++) {
            log.info("/hello,host: {},service_id: {}",instances.get(i).getHost(),instances.get(i).getServiceId());
        }
        return "Hello World";
    }

    /**
     *  用于测试Feign 调用服务提供者的方法(无参)
     * @return
     */
    @RequestMapping(value = "/hello",method = RequestMethod.GET)
    public String hello(){
        return "我是用于Feign消费者测试的服务提供者方法";
    }

    /**
     * 用于测试Feign 调用服务提供者 (一个参数,get请求)
     * @param name
     * @return
     */
    @RequestMapping(value = "/hello1", method = RequestMethod.GET)
    public String hello1(@RequestParam String name) {
        return "hello " + name + "!";
    }

    /**
     * 用于测试Feign 调用服务提供者 (多个参数,get请求)
     * @throws UnsupportedEncodingException
     */
    @RequestMapping(value = "/hello2", method = RequestMethod.GET)
    public Book hello2(@RequestHeader String name, @RequestHeader String author, @RequestHeader Integer price) throws UnsupportedEncodingException {
        Book book = new Book();
        book.setName(URLDecoder.decode(name, "UTF-8"));
        book.setAuthor(URLDecoder.decode(author, "UTF-8"));
        book.setPrice(price);
        System.out.println(book);
        return book;
    }

    /**
     *  用于测试Feign 调用服务提供者 (对象,post请求)
     * @param book
     * @return
     */
    @RequestMapping(value = "/hello3", method = RequestMethod.POST)
    public String hello3(@RequestBody Book book) {
        return "书名为：" + book.getName() + ";作者为：" + book.getAuthor();
    }

}
