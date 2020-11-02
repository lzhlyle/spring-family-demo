package com.lzhlyle.spring.demo.studyclientstarter.service.impl;

import com.lzhlyle.spring.demo.studyclientstarter.service.HelloService;
import org.springframework.stereotype.Service;

@Service
public class HelloServiceImpl implements HelloService {
    @Override
    public String sayHello() {
        return "hello!";
    }
}
