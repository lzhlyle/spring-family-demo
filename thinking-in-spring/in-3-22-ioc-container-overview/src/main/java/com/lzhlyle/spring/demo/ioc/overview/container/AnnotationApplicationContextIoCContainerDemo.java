package com.lzhlyle.spring.demo.ioc.overview.container;

import com.lzhlyle.spring.demo.ioc.overview.domain.User;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.ListableBeanFactory;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

public class AnnotationApplicationContextIoCContainerDemo {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        // 将当前 AnnotationApplicationContextIoCContainerDemo 类作为配置类
        applicationContext.register(AnnotationApplicationContextIoCContainerDemo.class);
        applicationContext.refresh();
        lookupByTypeForCollection(applicationContext);

        applicationContext.close();
    }

    // 通过 Java 注解定义 Bean
    @Bean
    public User user() {
        User user = new User();
        user.setId(2L);
        user.setName("lzhlyle");
        return user;
    }

    private static void lookupByTypeForCollection(BeanFactory beanFactory) {
        if (beanFactory instanceof ListableBeanFactory) {
            ListableBeanFactory listableBeanFactory = (ListableBeanFactory) beanFactory;
            Map<String, User> users = listableBeanFactory.getBeansOfType(User.class);
            System.out.println("lookupByTypeForCollection: " + users);
        }
    }
}
