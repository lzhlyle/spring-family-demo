package com.lzhlyle.spring.demo.dependency.source;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import javax.annotation.PostConstruct;

public class ResolvableDependencySourceDemo {
    @Autowired
    private String value;

    @PostConstruct
    public void init() {
        System.out.println(value);
    }

    public static void main(String[] args) {
//        solution1();
//        solution2();
    }

    private static void solution1() {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        // 此时已要求注入 value，但还未注册，会报 NoSuchBeanDefinitionException，故 addBeanFactoryPostProcessor 回调
        applicationContext.register(ResolvableDependencySourceDemo.class);
        applicationContext.addBeanFactoryPostProcessor(beanFactory -> {
            beanFactory.registerResolvableDependency(String.class, "hello-world");
        });
        applicationContext.refresh();
        applicationContext.close();
    }

    private static void solution2() {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        applicationContext.register(ResolvableDependencySourceDemo.class);
        ConfigurableListableBeanFactory beanFactory = applicationContext.getBeanFactory();
        beanFactory.registerResolvableDependency(String.class, "hello-world");
        applicationContext.refresh();
        applicationContext.close();
    }
}
