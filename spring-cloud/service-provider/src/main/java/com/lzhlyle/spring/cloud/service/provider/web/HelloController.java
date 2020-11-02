package com.lzhlyle.spring.cloud.service.provider.web;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class HelloController {
    @Value("${server.port}")
    private String port;

    @GetMapping(value = "/hello")
    public String index() {
        log.info(String.format("/hello, port:[%s]", port));
        return "Hello World";
    }
}
