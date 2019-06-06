package com.example.service;

import entity.Book;

/**
 * Feign中服务降级 回调方法类
 */
public class HelloServiceFallback implements  FeignService{
    @Override
    public String hello() {
        return "hello error";
    }

    @Override
    public String hello(String name) {
        return "error " + name;
    }

    @Override
    public Book hello(String name, String author, Integer price) {
        Book book = new Book();
        book.setName("error");
        return book;
    }

    @Override
    public String hello(Book book) {
        return "error book";
    }
}
