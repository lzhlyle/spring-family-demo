package com.lzhlyle.spring.demo.web;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoRestController {
    @GetMapping("/demo/get")
    public String index(Model model) {
        return "hello-world";
    }
}
