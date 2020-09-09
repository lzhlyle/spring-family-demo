package com.lzhlyle.spring.demo.bean.lifecycle;

import com.lzhlyle.spring.demo.ioc.overview.domain.User;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.support.PropertiesBeanDefinitionReader;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.EncodedResource;

public class BeanMetadataConfigurationDemo {
    public static void main(String[] args) {
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        PropertiesBeanDefinitionReader reader = new PropertiesBeanDefinitionReader(beanFactory);

        Resource resource = new ClassPathResource("META-INF/user.properties");
        EncodedResource encodedResource = new EncodedResource(resource, "utf-8");
        int beanCnt = reader.loadBeanDefinitions(encodedResource);
        System.out.println("beanCnt: " + beanCnt);

        User user = beanFactory.getBean("user", User.class);
        System.out.println(user);
    }
}
