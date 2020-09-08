package com.lzhlyle.spring.demo.dependency.source;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.io.Resource;

@Configuration
@PropertySource(value = "META-INF/default.properties", encoding = "utf-8")
public class ExternalConfigurationDependencySourceDemo {
    @Value("${user.id:-1}")
    private Long id;

    @Value("${usr.name:未知}")
    private String name;

    @Value("${user.resource:default-path}")
    private Resource resource;

    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        applicationContext.register(ExternalConfigurationDependencySourceDemo.class);
        applicationContext.refresh();

        ExternalConfigurationDependencySourceDemo demo =
                applicationContext.getBean(ExternalConfigurationDependencySourceDemo.class);

        System.out.println(demo.id);
        System.out.println(demo.name);
        System.out.println(demo.resource);

        applicationContext.close();
    }
}
