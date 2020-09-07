package com.lzhlyle.spring.demo.dependency.injection;

import com.lzhlyle.spring.demo.ioc.overview.domain.User;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.support.BeanDefinitionReader;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Set;

public class LazyAnnotationDependencyInjectionDemo {

    @Autowired
    private User user;

    @Autowired
    private ObjectProvider<User> userObjectProvider;

    @Autowired
    private ObjectFactory<Set<User>> userObjectFactory;

    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();

        BeanDefinitionReader reader = new XmlBeanDefinitionReader(applicationContext);
        String xmlResourcePath = "META-INF/dependency-lookup-context.xml";
        reader.loadBeanDefinitions(xmlResourcePath);

        applicationContext.register(LazyAnnotationDependencyInjectionDemo.class);
        applicationContext.refresh();

        LazyAnnotationDependencyInjectionDemo demo =
                applicationContext.getBean(LazyAnnotationDependencyInjectionDemo.class);

        System.out.println(demo.user);
        System.out.println(demo.userObjectProvider.getObject());
        System.out.println(demo.userObjectFactory.getObject());
        System.out.println();
        demo.userObjectProvider.forEach(System.out::println);

        applicationContext.close();
    }
}
