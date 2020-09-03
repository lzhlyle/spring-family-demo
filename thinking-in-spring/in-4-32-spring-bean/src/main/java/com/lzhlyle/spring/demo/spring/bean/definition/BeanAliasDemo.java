package com.lzhlyle.spring.demo.spring.bean.definition;

import com.lzhlyle.spring.demo.ioc.overview.domain.User;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class BeanAliasDemo {
    public static void main(String[] args) {
        BeanFactory beanFactory = new ClassPathXmlApplicationContext("classpath:/META-INF/bean-definition-context.xml");
        User user = beanFactory.getBean("user", User.class);
        User lzhUser = beanFactory.getBean("lzh-user", User.class);
        System.out.println(user == lzhUser); // true
    }
}
