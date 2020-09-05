package com.lzhlyle.spring.demo.dependency.lookup;

import com.lzhlyle.spring.demo.ioc.overview.domain.User;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.ListableBeanFactory;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class TypeSafetyDependencyLookupDemo {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        applicationContext.register(TypeSafetyDependencyLookupDemo.class);
        applicationContext.refresh();

        displayBeanFactoryGetBean(applicationContext);
        displayObjectFactoryGetObject(applicationContext);
        displayObjectProviderGetIfAvailable(applicationContext);
        displayListableBeanFactoryGetBeansOfType(applicationContext);
        displayObjectProviderStream(applicationContext);

        applicationContext.close();
    }

    private static void displayObjectProviderStream(BeanFactory beanFactory) {
        ObjectProvider<User> objectProvider = beanFactory.getBeanProvider(User.class);
        printBeansException("displayObjectProviderStream", objectProvider::stream);
    }

    private static void displayListableBeanFactoryGetBeansOfType(ListableBeanFactory listableBeanFactory) {
        printBeansException("displayListableBeanFactoryGetBeansOfType", () -> listableBeanFactory.getBeansOfType(User.class));
    }

    private static void displayObjectProviderGetIfAvailable(BeanFactory beanFactory) {
        ObjectProvider<User> objectProvider = beanFactory.getBeanProvider(User.class);
        printBeansException("displayObjectProviderIfAvailable", objectProvider::getIfAvailable);
    }

    private static void displayObjectFactoryGetObject(BeanFactory beanFactory) {
        ObjectFactory<User> objectFactory = beanFactory.getBeanProvider(User.class);
        printBeansException("displayObjectFactoryGetObject", objectFactory::getObject);
    }

    private static void displayBeanFactoryGetBean(BeanFactory beanFactory) {
        printBeansException("displayBeanFactoryGetBean", () -> beanFactory.getBean(User.class));
    }

    private static void printBeansException(String source, Runnable runnable) {
        System.err.println("source from: " + source);
        try {
            runnable.run();
        } catch (BeansException e) {
            e.printStackTrace();
        }
    }
}
