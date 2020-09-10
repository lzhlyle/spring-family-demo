package com.lzhlyle.spring.demo.bean.lifecycle;

import com.lzhlyle.spring.demo.bean.lifecycle.bean.post.processor.MyInstantiationAwareBeanPostProcessor;
import com.lzhlyle.spring.demo.bean.lifecycle.domain.UserHolder;
import com.lzhlyle.spring.demo.ioc.overview.domain.SuperUser;
import com.lzhlyle.spring.demo.ioc.overview.domain.User;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class BeanInstantiationLifecycleDemo {
    public static void main(String[] args) {
        executeBeanFactory();
        System.out.println("=============");
        executeApplicationContext();
    }

    private static void executeApplicationContext() {
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext();
        String[] paths = {"META-INF/dependency-lookup-context.xml", "META-INF/bean-constructor-dependency-injection-context.xml"};
        applicationContext.setConfigLocations(paths);
        applicationContext.refresh();

        BeanFactory beanFactory = applicationContext.getBeanFactory();
        User user = beanFactory.getBean("user", User.class);
        System.out.println(user);

        SuperUser superUser = beanFactory.getBean("superUser", SuperUser.class);
        System.out.println(superUser);

        UserHolder userHolder = beanFactory.getBean("userHolder", UserHolder.class);
        System.out.println(userHolder);

        applicationContext.close();
    }

    private static void executeBeanFactory() {
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();

//        beanFactory.addBeanPostProcessor(new MyInstantiationAwareBeanPostProcessor()); // 可通过 xml 声明 bean 注入

        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(beanFactory);
        String[] paths = {"META-INF/dependency-lookup-context.xml", "META-INF/bean-constructor-dependency-injection-context.xml"};
        reader.loadBeanDefinitions(paths);

        beanFactory.addBeanPostProcessor(beanFactory.getBean(MyInstantiationAwareBeanPostProcessor.class));

        User user = beanFactory.getBean("user", User.class);
        System.out.println(user);

        SuperUser superUser = beanFactory.getBean("superUser", SuperUser.class);
        System.out.println(superUser);

        UserHolder userHolder = beanFactory.getBean("userHolder", UserHolder.class);
        System.out.println(userHolder);
    }
}
