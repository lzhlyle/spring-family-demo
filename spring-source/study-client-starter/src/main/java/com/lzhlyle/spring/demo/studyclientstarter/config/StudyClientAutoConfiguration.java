package com.lzhlyle.spring.demo.studyclientstarter.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan({"com.lzhlyle.spring.demo.studyclientstarter.service"})
@ConditionalOnProperty(prefix = "study", name = "enabled", havingValue = "true")
public class StudyClientAutoConfiguration {
}
