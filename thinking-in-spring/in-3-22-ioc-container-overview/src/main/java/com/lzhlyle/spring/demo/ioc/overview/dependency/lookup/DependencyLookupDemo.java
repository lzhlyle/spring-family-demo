package com.lzhlyle.spring.demo.ioc.overview.dependency.lookup;

import com.lzhlyle.spring.demo.ioc.overview.annotation.Super;
import com.lzhlyle.spring.demo.ioc.overview.domain.User;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.ListableBeanFactory;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Map;

public class DependencyLookupDemo {
    public static void main(String[] args) {
        BeanFactory beanFactory = new ClassPathXmlApplicationContext("META-INF/dependency-lookup-context.xml");
        lookupByNameInRealTime(beanFactory);
        lookupByNameInLazy(beanFactory);
        lookupByTypeForSingle(beanFactory);
        lookupByTypeForCollection(beanFactory);
        lookupByAnnotationForCollection(beanFactory);
    }

    private static void lookupByAnnotationForCollection(BeanFactory beanFactory) {
        if (beanFactory instanceof ListableBeanFactory) {
            ListableBeanFactory listableBeanFactory = (ListableBeanFactory) beanFactory;
            Map<String, Super> user = (Map) listableBeanFactory.getBeansWithAnnotation(Super.class);
            System.out.println("lookupByAnnotationForCollection: " + user);
        }
    }

    private static void lookupByTypeForSingle(BeanFactory beanFactory) {
        User user = beanFactory.getBean(User.class);
        System.out.println("lookupByTypeForSingle: " + user);
    }

    private static void lookupByTypeForCollection(BeanFactory beanFactory) {
        if (beanFactory instanceof ListableBeanFactory) {
            ListableBeanFactory listableBeanFactory = (ListableBeanFactory) beanFactory;
            Map<String, User> users = listableBeanFactory.getBeansOfType(User.class);
            System.out.println("lookupByTypeForCollection: " + users);
        }
    }

    private static void lookupByNameInLazy(BeanFactory beanFactory) {
        ObjectFactory<User> objectFactory = (ObjectFactory<User>) beanFactory.getBean("objectFactory");
        User user = objectFactory.getObject();
        System.out.println("lookupByNameInLazy: " + user);
    }

    private static void lookupByNameInRealTime(BeanFactory beanFactory) {
        User user = (User) beanFactory.getBean("user");
        System.out.println("lookupByNameInRealTime: " + user);
    }
}
