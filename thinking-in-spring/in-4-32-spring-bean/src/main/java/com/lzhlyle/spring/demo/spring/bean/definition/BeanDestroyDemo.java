package com.lzhlyle.spring.demo.spring.bean.definition;

import com.lzhlyle.spring.demo.spring.bean.definition.factory.DefaultUserFactory;
import com.lzhlyle.spring.demo.spring.bean.definition.factory.IUserFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Lazy;

public class BeanDestroyDemo {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        applicationContext.register(BeanDestroyDemo.class);
        applicationContext.refresh();
        IUserFactory userFactory = applicationContext.getBean(IUserFactory.class);
        System.out.println("Spring ready to close.");
        applicationContext.close();
        System.out.println("Spring closed.");
    }

    @Bean(destroyMethod = "destroyByCustom")
    public DefaultUserFactory userFactory() {
        return new DefaultUserFactory();
    }
}
