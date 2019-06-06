package com.example;

import com.netflix.hystrix.contrib.javanica.aop.aspectj.HystrixCacheAspect;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;
/**
 * SpringCloud  Robbin 服务消费者
 * 使用Ribbon是一个负载均衡客户端 类似nginx反向代理，可以很好的控制htt和tcp的一些行为。Feign默认集成了ribbon。
 * Ribbon中默认采用轮询的方式去调用服务提供者，进而实现了客户端的负载均衡。
 *
 * Feign 采用的是基于接口的注解
 * Feign 整合了ribbon
 *
 * 一般情况下我们所说的负载均衡通常都是指服务端负载均衡，服务端负载均衡又分为两种，一种是硬件负载均衡，还有一种是软件负载均衡。
 * 硬件负载均衡主要通过在服务器节点之间安装专门用于负载均衡的设备，常见的如F5。
 * 软件负载均衡则主要是在服务器上安装一些具有负载均衡功能的软件来完成请求分发进而实现负载均衡，常见的就是Nginx。
 *
 * 当客户端的请求到达负载均衡服务器时，负载均衡服务器按照某种配置好的规则从可用服务端清单中选出一台服务器去处理客户端的请求
 *
 * 客户端负载均衡和服务端负载均衡最大的区别在于服务清单所存储的位置。在客户端负载均衡中，
 * 所有的客户端节点都有一份自己要访问的服务端清单，这些清单统统都是从Eureka服务注册中心获取的。
 */
//亮明Eureka客户端身份  表示该应用是一个Eureka客户端应用，这样该应用就自动具备了发现服务的能力。
@EnableDiscoveryClient
@SpringBootApplication
//通过@EnableCircuitBreaker开启断路器功能
@EnableCircuitBreaker
//可以使用@SpringCloudApplication 代替上面3个注解
public class RibbonConsumerApplication {

    public static void main(String[] args) {
        SpringApplication.run(RibbonConsumerApplication.class, args);
    }

    @Bean //注入spring
    @LoadBalanced  //开启开启客户端负载均衡。
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }


    /**
     * Hystrix使用注解来实现异步请求 (直接使用默认是同步请求)
     */
    @Bean
    public HystrixCacheAspect hystrixCacheAspect(){
        return new HystrixCacheAspect();
    }
}

