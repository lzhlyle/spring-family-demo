package com.lzhlyle.spring.demo.spring.bean.definition;

import com.lzhlyle.spring.demo.spring.bean.definition.factory.DefaultUserFactory;
import com.lzhlyle.spring.demo.spring.bean.definition.factory.IUserFactory;
import org.springframework.beans.factory.config.SingletonBeanRegistry;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class SingletonBeanRegistrationDemo {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();

        // 外部对象
        IUserFactory userFactory = new DefaultUserFactory();
        SingletonBeanRegistry beanFactory = applicationContext.getBeanFactory();
        beanFactory.registerSingleton("userFactory", userFactory);

        applicationContext.refresh();

        IUserFactory lookupUserFactory = applicationContext.getBean("userFactory", IUserFactory.class);
        System.out.println(lookupUserFactory == userFactory); // true

        applicationContext.close();
    }
}
