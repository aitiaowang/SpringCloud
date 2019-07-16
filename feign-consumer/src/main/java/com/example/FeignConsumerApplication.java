package com.example;

import feign.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

/**
 * feign 基于接口调用 Feign是对Ribbon和Hystrix的整合
 * @EnableFeignClients注解表示开启Spring Cloud Feign的支持功能
 * @EnableDiscoveryClient 注册中心注册
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
@EnableCircuitBreaker
public class FeignConsumerApplication {

    public static void main(String[] args) {

        SpringApplication.run(FeignConsumerApplication.class, args);
    }

    /**
     * Feign为每一个FeignClient都提供了一个feign.Logger实例，我们可以在配置中开启日志
     * 第一步：application.properties中配置日志输出
     * application.properties中配置如下内容，表示设置日志输出级别：
     * # 开启日志 格式为logging.level.+Feign客户端路径
     * logging.level.org.sang.HelloService=debug
     * 第二步：入口类中配置日志Bean
     * 入口类中配置日志Bean，如下：
     */
    @Bean
    Logger.Level feignLoggerLevel() {
        return Logger.Level.FULL;
    }
}

