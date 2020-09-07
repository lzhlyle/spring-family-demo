package com.lzhlyle.spring.demo.dependency.injection;

import com.lzhlyle.spring.demo.ioc.overview.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.support.BeanDefinitionReader;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Lazy;

import javax.inject.Inject;
import java.util.Map;
import java.util.Optional;

public class AnnotationDependencyResolutionInjectionDemo {
    @Autowired
    @Lazy
    private User lazyUser;

    @Autowired
    private User user;
    // DependencyDescriptor:
    // 必须 eager = true
    // 实时 required = true
    // 通过类型 User.class 依赖查找
    // 字段名称 "user"
    // 首要 primary = true

    @Autowired
    private Map<String, User> users;

    @Autowired
    private Optional<User> userOptional;

    @Inject
    private User injectUser;

    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();

        BeanDefinitionReader reader = new XmlBeanDefinitionReader(applicationContext);
        String xmlResourcePath = "META-INF/dependency-lookup-context.xml";
        reader.loadBeanDefinitions(xmlResourcePath);

        applicationContext.register(AnnotationDependencyResolutionInjectionDemo.class);
        applicationContext.refresh();

        AnnotationDependencyResolutionInjectionDemo demo =
                applicationContext.getBean(AnnotationDependencyResolutionInjectionDemo.class);

        System.out.println(demo.lazyUser);
        System.out.println(demo.user);
        System.out.println(demo.users);
        System.out.println(demo.userOptional);
        System.out.println(demo.injectUser);

        applicationContext.close();
    }
}
