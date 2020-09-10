package com.lzhlyle.spring.demo.bean.lifecycle;

import com.lzhlyle.spring.demo.bean.lifecycle.bean.post.processor.MyInstantiationAwareBeanPostProcessor;
import com.lzhlyle.spring.demo.bean.lifecycle.domain.UserHolder;
import com.lzhlyle.spring.demo.ioc.overview.domain.SuperUser;
import com.lzhlyle.spring.demo.ioc.overview.domain.User;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.annotation.CommonAnnotationBeanPostProcessor;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class BeanInitializationLifecycleDemo {
    public static void main(String[] args) {
        executeBeanFactory();
    }

    private static void executeBeanFactory() {
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        beanFactory.addBeanPostProcessor(new MyInstantiationAwareBeanPostProcessor());
        beanFactory.addBeanPostProcessor(new CommonAnnotationBeanPostProcessor());

        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(beanFactory);
        String[] paths = {"META-INF/dependency-lookup-context.xml", "META-INF/bean-constructor-dependency-injection-context.xml"};
        reader.loadBeanDefinitions(paths);

        User user = beanFactory.getBean("user", User.class);
        System.out.println(user);

        SuperUser superUser = beanFactory.getBean("superUser", SuperUser.class);
        System.out.println(superUser);

        UserHolder userHolder = beanFactory.getBean("userHolder", UserHolder.class);
        beanFactory.preInstantiateSingletons();
        System.out.println(userHolder);
    }
}
