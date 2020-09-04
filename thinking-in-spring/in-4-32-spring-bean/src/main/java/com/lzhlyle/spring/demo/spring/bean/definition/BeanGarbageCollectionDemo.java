package com.lzhlyle.spring.demo.spring.bean.definition;

import com.lzhlyle.spring.demo.spring.bean.definition.factory.DefaultUserFactory;
import com.lzhlyle.spring.demo.spring.bean.definition.factory.IUserFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

public class BeanGarbageCollectionDemo {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        applicationContext.register(BeanGarbageCollectionDemo.class);
        applicationContext.refresh();
        applicationContext.close();
        System.gc();
    }

    @Bean
    public IUserFactory userFactory() {
        return new DefaultUserFactory();
    }
}
