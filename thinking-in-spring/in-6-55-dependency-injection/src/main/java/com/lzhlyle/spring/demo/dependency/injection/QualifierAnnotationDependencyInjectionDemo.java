package com.lzhlyle.spring.demo.dependency.injection;

import com.lzhlyle.spring.demo.dependency.injection.annotation.UserGroup;
import com.lzhlyle.spring.demo.ioc.overview.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.support.BeanDefinitionReader;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

import java.util.Collection;

public class QualifierAnnotationDependencyInjectionDemo {
    @Autowired
    private User user;

    @Autowired
    @Qualifier("user") // 限定
    private User namedUser;

    @Bean
    @Qualifier // 分组
    public User user1() {
        return createUser(111L);
    }

    @Bean
    @Qualifier // 分组
    public User user2() {
        return createUser(222L);
    }

    @Bean
    @Qualifier("group3") // 分组
    public User user3() {
        return createUser(333L);
    }

    @Bean
    @UserGroup
    public User user4() {
        return createUser(444L);
    }

    private static User createUser(Long id) {
        return new User() {{
            setId(id);
        }};
    }

    @Autowired
    private Collection<User> notQualifierUsers;

    @Autowired
    @Qualifier
    private Collection<User> qualifierUser;

    @Autowired
    @Qualifier("group3")
    private Collection<User> group3Qualifieruser;

    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        applicationContext.register(QualifierAnnotationDependencyInjectionDemo.class);

        BeanDefinitionReader reader = new XmlBeanDefinitionReader(applicationContext);
        String xmlResourcePath = "META-INF/dependency-lookup-context.xml";
        reader.loadBeanDefinitions(xmlResourcePath);

        applicationContext.refresh();

        QualifierAnnotationDependencyInjectionDemo demo =
                applicationContext.getBean(QualifierAnnotationDependencyInjectionDemo.class);

        System.out.println(demo.user);
        System.out.println(demo.namedUser);
        System.out.println(demo.notQualifierUsers);
        System.out.println(demo.qualifierUser);
        System.out.println(demo.group3Qualifieruser);

        applicationContext.close();
    }
}
