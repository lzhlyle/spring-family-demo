package com.lzhlyle.spring.cloud.service.client.web;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@Slf4j
@RestController
public class ConsumerController {

    @Autowired
    private DiscoveryClient client;

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping(value = "/consumer")
    public String helloConsumer() {
        String uri = "http://HELLO-SERVICE/hello";
        return restTemplate.getForEntity(uri, String.class).getBody();
    }
}
