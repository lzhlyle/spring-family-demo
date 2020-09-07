package com.lzhlyle.spring.demo.dependency.injection;

import com.lzhlyle.spring.demo.dependency.injection.annotation.InjectedUser;
import com.lzhlyle.spring.demo.dependency.injection.annotation.MyAutowired;
import com.lzhlyle.spring.demo.ioc.overview.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.AutowiredAnnotationBeanPostProcessor;
import org.springframework.beans.factory.support.BeanDefinitionReader;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Lazy;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;

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

    @MyAutowired
    private Optional<User> userOptional;

    @Inject
    private User injectUser;

    @InjectedUser
    private User customInjectedUser;

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
        System.out.println(demo.customInjectedUser);

        applicationContext.close();
    }

    // static: Bean 定义周期的问题，在当前 Demo 类未实例化之前，就可以处理
//    @Bean(name = AUTOWIRED_ANNOTATION_PROCESSOR_BEAN_NAME)
//    public static AutowiredAnnotationBeanPostProcessor beanPostProcessor() {
//        AutowiredAnnotationBeanPostProcessor beanPostProcessor = new AutowiredAnnotationBeanPostProcessor();
//        // 新增注解处理
//        Set<Class<? extends Annotation>> autowiredAnnotationTypes =
//                new LinkedHashSet<>(Arrays.asList(Autowired.class, Inject.class, InjectedUser.class));
//        beanPostProcessor.setAutowiredAnnotationTypes(autowiredAnnotationTypes);
//        return beanPostProcessor;
//    }

    @Bean
    @Order(Ordered.LOWEST_PRECEDENCE - 3)
    public static AutowiredAnnotationBeanPostProcessor beanPostProcessor() {
        AutowiredAnnotationBeanPostProcessor beanPostProcessor = new AutowiredAnnotationBeanPostProcessor();
        beanPostProcessor.setAutowiredAnnotationType(InjectedUser.class);
        return beanPostProcessor;
    }
}
