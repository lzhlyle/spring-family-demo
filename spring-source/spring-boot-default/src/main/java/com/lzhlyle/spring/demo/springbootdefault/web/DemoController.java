package com.lzhlyle.spring.demo.springbootdefault.web;

import com.lzhlyle.spring.demo.studyclientstarter.service.HelloService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/demo")
public class DemoController {
    @Resource
    private HelloService helloService;

    @GetMapping("/get")
    public String get() {
        return "get here.";
    }

    @GetMapping("/hello")
    public String hello() {
        return helloService.sayHello();
    }
}
