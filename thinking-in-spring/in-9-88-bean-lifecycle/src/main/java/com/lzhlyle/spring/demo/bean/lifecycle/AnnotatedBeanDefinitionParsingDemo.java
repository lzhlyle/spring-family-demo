package com.lzhlyle.spring.demo.bean.lifecycle;

import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.context.annotation.AnnotatedBeanDefinitionReader;

public class AnnotatedBeanDefinitionParsingDemo {
    public static void main(String[] args) {
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        AnnotatedBeanDefinitionReader reader = new AnnotatedBeanDefinitionReader(beanFactory);

        int beanDefinitionCountBefore = beanFactory.getBeanDefinitionCount();
        reader.register(AnnotatedBeanDefinitionParsingDemo.class);
        int beanDefinitionCountAfter = beanFactory.getBeanDefinitionCount();
        int cnt = beanDefinitionCountAfter - beanDefinitionCountBefore;
        System.out.println("cnt: " + cnt);

        AnnotatedBeanDefinitionParsingDemo demo = beanFactory.getBean(AnnotatedBeanDefinitionParsingDemo.class);
        System.out.println(demo);
    }
}
