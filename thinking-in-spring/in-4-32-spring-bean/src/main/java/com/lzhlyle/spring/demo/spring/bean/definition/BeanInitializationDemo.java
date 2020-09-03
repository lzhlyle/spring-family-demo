package com.lzhlyle.spring.demo.spring.bean.definition;

import com.lzhlyle.spring.demo.spring.bean.definition.factory.DefaultUserFactory;
import com.lzhlyle.spring.demo.spring.bean.definition.factory.IUserFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;

@Configuration
public class BeanInitializationDemo {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        applicationContext.register(BeanInitializationDemo.class);
        applicationContext.refresh();
        System.out.println("Spring refreshed.");

        IUserFactory userFactory = applicationContext.getBean(IUserFactory.class);
        // 会调用 DefaultUserFactory::init

        System.out.println(userFactory);
        applicationContext.close();
    }

    @Bean(initMethod = "initByCustomWithAnnotation")
    @Lazy
    public DefaultUserFactory userFactory() {
        return new DefaultUserFactory();
    }
}
