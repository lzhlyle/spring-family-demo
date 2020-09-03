package com.lzhlyle.spring.demo.spring.bean.definition;

import com.lzhlyle.spring.demo.ioc.overview.domain.User;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionReaderUtils;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

@Import(AnnotationBeanDefinitionDemo.ConfigForImport.class)
public class AnnotationBeanDefinitionDemo {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();

//        registerConfig(applicationContext);
//        scanComponent(applicationContext);
//        registerImport(applicationContext);

//        registerWithBeanNameByAPI(applicationContext);
//        registerWithoutBeanNameByAPI(applicationContext);

        applicationContext.close();
    }

    private static void registerWithBeanNameByAPI(AnnotationConfigApplicationContext applicationContext) {
        registerBeanDefinition(applicationContext, "api-user", User.class);
        applicationContext.refresh();
        User user = applicationContext.getBean("api-user", User.class);
        System.out.println(user);
    }

    private static void registerWithoutBeanNameByAPI(AnnotationConfigApplicationContext applicationContext) {
        registerBeanDefinition(applicationContext, User.class);
        applicationContext.refresh();
        User user = applicationContext.getBean(User.class);
        System.out.println(user);
    }

    private static void registerBeanDefinition(BeanDefinitionRegistry registry, String beanName, Class<?> beanClass) {
        BeanDefinitionBuilder builder = BeanDefinitionBuilder.genericBeanDefinition(beanClass);
        builder.addPropertyValue("id", 3L).addPropertyValue("name", "api");
        if (StringUtils.hasText(beanName)) {
            registry.registerBeanDefinition(beanName, builder.getBeanDefinition());
        } else {
            BeanDefinitionReaderUtils.registerWithGeneratedName(builder.getBeanDefinition(), registry);
        }
    }

    private static void registerBeanDefinition(BeanDefinitionRegistry registry, Class<?> beanClass) {
        registerBeanDefinition(registry, null, beanClass);
    }

    private static void registerImport(AnnotationConfigApplicationContext applicationContext) {
        applicationContext.register(AnnotationBeanDefinitionDemo.class);
        applicationContext.refresh();
        User user = applicationContext.getBean("import-user", User.class);
        System.out.println(user);
    }

    private static void scanComponent(AnnotationConfigApplicationContext applicationContext) {
        applicationContext.scan("com.lzhlyle.spring.demo.spring.bean.*");
        applicationContext.refresh();
        User user = applicationContext.getBean("component-user", User.class);
        System.out.println(user);
    }

    private static void registerConfig(AnnotationConfigApplicationContext applicationContext) {
        applicationContext.register(ConfigForRegister.class);
        applicationContext.refresh();
        User user = applicationContext.getBean("register-user", User.class);
        System.out.println(user);
    }

    public static class ConfigForImport {
        @Bean(name = {"user", "import-user"})
        public User user() {
            User user = new User();
            user.setId(2L);
            user.setName("import");
            return user;
        }
    }

    @Component
    private static class ConfigForComponent {
        @Bean(name = {"user", "component-user"})
        public User user() {
            User user = new User();
            user.setId(2L);
            user.setName("component");
            return user;
        }
    }

    // 配置类，代替 XML 文件
    private static class ConfigForRegister {
        @Bean(name = {"user", "register-user"})
        public User user() {
            User user = new User();
            user.setId(2L);
            user.setName("register");
            return user;
        }
    }
}
