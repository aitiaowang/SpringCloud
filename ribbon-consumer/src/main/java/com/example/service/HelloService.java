package com.example.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * 服务消费者具体实现 及 断路器
 */
@Service
public class HelloService {
    @Autowired
    private RestTemplate restTemplate;

    /**
     * 通过@HystrixCommand注解来指定请求失败时回调的方法。
     * 直接使用注解来配置Hystrix,是一种同步请求的方式
     * ignoreExceptions => 有一个异常抛出后不进入到服务降级方法中去处理，而是直接将异常抛给用户
     */
  //  @HystrixCommand(fallbackMethod = "error",ignoreExceptions = ArithmeticException.class)
    @HystrixCommand(fallbackMethod = "error")
    public String hello() {
        ResponseEntity<String> forEntity = restTemplate.getForEntity("http://HELLO-SERVICE/hello", String.class);
        return forEntity.getBody();
    }

    /**
     * error方法是一个请求失败时回调的方法。
     *
     * @return
     */
    @HystrixCommand
    public String error() {
        return "服务提供者错误,断路器执行回调";
    }
}
