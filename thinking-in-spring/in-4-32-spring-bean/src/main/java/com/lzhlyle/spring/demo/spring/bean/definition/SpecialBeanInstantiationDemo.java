package com.lzhlyle.spring.demo.spring.bean.definition;

import com.lzhlyle.spring.demo.ioc.overview.domain.User;
import com.lzhlyle.spring.demo.spring.bean.definition.factory.DefaultUserFactory;
import com.lzhlyle.spring.demo.spring.bean.definition.factory.IUserFactory;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.ServiceLoader;

public class SpecialBeanInstantiationDemo {
    public static void main(String[] args) {
        serviceLoader();

        ApplicationContext beanFactory = new ClassPathXmlApplicationContext("META-INF/special-bean-instantiation-context.xml");
        fromServiceLoaderFactoryBean(beanFactory);

        fromAutoWireCapableBeanFactory(beanFactory);
    }

    private static void fromAutoWireCapableBeanFactory(ApplicationContext beanFactory) {
        AutowireCapableBeanFactory autowireCapableBeanFactory = beanFactory.getAutowireCapableBeanFactory();
        IUserFactory userFactory = autowireCapableBeanFactory.createBean(DefaultUserFactory.class);
        User user = userFactory.createUser();
        System.out.println(user);
    }

    private static void fromServiceLoaderFactoryBean(ApplicationContext beanFactory) {
        ServiceLoader<IUserFactory> serviceLoader = beanFactory.getBean("user-factory-service-loader", ServiceLoader.class);
        printUser(serviceLoader);
    }

    private static void serviceLoader() {
        ServiceLoader<IUserFactory> serviceLoader = ServiceLoader.load(IUserFactory.class, Thread.currentThread().getContextClassLoader());
        printUser(serviceLoader);
    }

    private static void printUser(ServiceLoader<IUserFactory> serviceLoader) {
        for (IUserFactory factory : serviceLoader) {
            User user = factory.createUser();
            System.out.println(user);
        }
    }
}
