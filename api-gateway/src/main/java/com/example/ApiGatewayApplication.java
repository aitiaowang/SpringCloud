package com.example;

import com.example.filter.PermisFilter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;

/**
 * Zuul 网关
 * API网关作为系统的的统一入口，将微服务中的内部细节都屏蔽掉了，而且能够自动的维护服务实例，实现负载均衡的路由转发
 * 同时，它提供的过滤器为所有的微服务提供统一的权限校验机制，使得服务自身只需要关注业务逻辑即可。
 */
@SpringBootApplication
//添加@EnableZuulProxy注解表示开启Zuul的API网关服务功能
@EnableZuulProxy
public class ApiGatewayApplication {

    public static void main(String[] args) {

        SpringApplication.run(ApiGatewayApplication.class, args);
    }

    /**
     * 配置过滤器Bean
     */
    @Bean
    PermisFilter permisFilter() {
        return new PermisFilter();
    }
}

