package com.lzhlyle.spring.demo.ioc.overview.container;

import com.lzhlyle.spring.demo.ioc.overview.domain.User;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.ListableBeanFactory;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;

import java.util.Map;

public class BeanFactoryIoCContainerDemo {
    public static void main(String[] args) {
        // 创建容器
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(beanFactory);
        String path = "META-INF/dependency-lookup-context.xml";
        // 装载 Bean 定义
        int count = reader.loadBeanDefinitions(path); // 3，已装载
        System.out.println(count);
        lookupByTypeForCollection(beanFactory);
    }

    private static void lookupByTypeForCollection(BeanFactory beanFactory) {
        if (beanFactory instanceof ListableBeanFactory) {
            ListableBeanFactory listableBeanFactory = (ListableBeanFactory) beanFactory;
            Map<String, User> users = listableBeanFactory.getBeansOfType(User.class);
            System.out.println("lookupByTypeForCollection: " + users);
        }
    }
}
