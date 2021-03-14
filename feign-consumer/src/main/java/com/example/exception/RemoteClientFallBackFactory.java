package com.example.exception;

import com.example.service.FeignService;
import com.google.common.base.Strings;
import entity.Book;
import feign.hystrix.FallbackFactory;

/**
 * @Description: 远程调用统一异常处理
 * @Author: sxk
 * @CreateDate: 2021/3/14 22:09
 * @Version: 1.0
 */
public class RemoteClientFallBackFactory implements FallbackFactory<FeignService> {
    @Override
    public FeignService create(Throwable throwable) {
        return new FeignService() {
            @Override
            public void testFeignLoadBalance() {
                String message = throwable.getMessage();
                System.out.println("Feign远程调用异常： " + message);
                return;
            }

            @Override
            public String hello() {
                String message = throwable.getMessage();
                System.out.println("Feign远程调用异常： " + message);
                return "";
            }

            @Override
            public String hello(String name) {
                String message = throwable.getMessage();
                System.out.println("Feign远程调用异常： " + message);
                return "";
            }

            @Override
            public Book hello(String name, String author, Integer price) {
                String message = throwable.getMessage();
                System.out.println("Feign远程调用异常： " + message);
                return null;
            }

            @Override
            public String hello(Book book) {
                String message = throwable.getMessage();
                System.out.println("Feign远程调用异常： " + message);
                return "";
            }
        };
    }
}
