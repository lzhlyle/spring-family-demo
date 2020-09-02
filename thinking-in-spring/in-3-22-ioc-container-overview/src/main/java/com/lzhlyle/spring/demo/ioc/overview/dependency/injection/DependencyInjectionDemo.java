package com.lzhlyle.spring.demo.ioc.overview.dependency.injection;

import com.lzhlyle.spring.demo.ioc.overview.repository.UserRepository;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.env.Environment;

public class DependencyInjectionDemo {
    public static void main(String[] args) {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("META-INF/dependency-injection-context.xml");
        UserRepository userRepository = applicationContext.getBean("userRepository", UserRepository.class);

        // 自定义 Bean
        System.out.println(userRepository.getUsers());

        // 内建依赖，非 Bean
        System.out.println(userRepository.getBeanFactory());
        System.out.println(userRepository.getBeanFactory() == applicationContext); // false
        System.out.println(userRepository.getBeanFactory() == applicationContext.getAutowireCapableBeanFactory()); // true

//        System.out.println(beanFactory.getBean(BeanFactory.class)); // exception

        ObjectFactory objectFactory = userRepository.getObjectFactory();
        System.out.println(objectFactory.getObject());
        System.out.println(objectFactory.getObject() == applicationContext); // true

        // 容器内建 Bean
        Environment environment = applicationContext.getBean(Environment.class);
        System.out.println(environment);
    }
}
