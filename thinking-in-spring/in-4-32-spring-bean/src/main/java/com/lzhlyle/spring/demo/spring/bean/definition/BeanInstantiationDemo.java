package com.lzhlyle.spring.demo.spring.bean.definition;

import com.lzhlyle.spring.demo.ioc.overview.domain.User;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class BeanInstantiationDemo {
    public static void main(String[] args) {
        BeanFactory beanFactory = new ClassPathXmlApplicationContext("META-INF/bean-instantiation-context.xml");
        instantiateByStaticMethod(beanFactory);
        instantiateByInstanceMethod(beanFactory);
        instantiateByFactoryBean(beanFactory);
    }

    private static void instantiateByFactoryBean(BeanFactory beanFactory) {
        User user = beanFactory.getBean("user-by-factory-bean", User.class);
        System.out.println(user);
    }

    private static void instantiateByInstanceMethod(BeanFactory beanFactory) {
        User user = beanFactory.getBean("user-by-instance-method", User.class);
        System.out.println(user);
    }

    private static void instantiateByStaticMethod(BeanFactory beanFactory) {
        User user = beanFactory.getBean("user-by-static-method", User.class);
        System.out.println(user);
    }
}
