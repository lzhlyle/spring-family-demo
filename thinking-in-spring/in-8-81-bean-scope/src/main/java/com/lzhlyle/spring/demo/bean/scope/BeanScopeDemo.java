package com.lzhlyle.spring.demo.bean.scope;

import com.lzhlyle.spring.demo.ioc.overview.domain.User;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;

import java.util.Map;

public class BeanScopeDemo implements DisposableBean {
    // 声明
    @Bean
    @Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
    public static User singletonUser() {
        return creageUser();
    }

    @Bean
    @Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
    public static User prototypeUser() {
        return creageUser();
    }

    private static User creageUser() {
        User user = new User();
        user.setId(System.nanoTime());
        return user;
    }

    // 依赖注入
    @Autowired
    @Qualifier("singletonUser")
    private User singletonUser;

    @Autowired
    @Qualifier("singletonUser")
    private User singletonUser2;

    @Autowired
    @Qualifier("prototypeUser")
    private User prototypeUser;

    @Autowired
    @Qualifier("prototypeUser")
    private User prototypeUser1;

    @Autowired
    @Qualifier("prototypeUser")
    private User prototypeUser2;

    @Autowired
    private Map<String, User> users;

    @Autowired
    private ConfigurableListableBeanFactory beanFactory; // resolvable dependency

    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        applicationContext.register(BeanScopeDemo.class);

        // 不推荐
//        applicationContext.addBeanFactoryPostProcessor(beanFactory -> {
//            beanFactory.addBeanPostProcessor(new BeanPostProcessor() {
//                @Override
//                public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
//                    System.out.printf("%s Bean [%s] postProcessAfterInitialization...%n", bean.getClass().getName(), beanName);
//                    return bean;
//                }
//            });
//        });

        applicationContext.refresh();

//        scopedBeansByLookup(applicationContext);
        scopedBeansByInjection(applicationContext);

        applicationContext.close();
    }

    private static void scopedBeansByInjection(AnnotationConfigApplicationContext applicationContext) {
        BeanScopeDemo demo = applicationContext.getBean(BeanScopeDemo.class);
        System.out.println("singletonUser" + demo.singletonUser);
        System.out.println("singletonUser2" + demo.singletonUser2);
        System.out.println("prototypeUser" + demo.prototypeUser);
        System.out.println("prototypeUser1" + demo.prototypeUser1);
        System.out.println("prototypeUser2" + demo.prototypeUser2);
        System.out.println("users: " + demo.users);
    }

    // 依赖查找
    private static void scopedBeansByLookup(AnnotationConfigApplicationContext applicationContext) {
        for (int i = 0; i < 3; i++) {
            User singletonUser = applicationContext.getBean("singletonUser", User.class);
            System.out.println(singletonUser);

            User prototypeUser = applicationContext.getBean("prototypeUser", User.class);
            System.out.println(prototypeUser);
        }
    }

    @Override
    public void destroy() throws Exception {
        System.out.println("demo destroying...");
        this.prototypeUser.destroy();
        this.prototypeUser1.destroy();
        this.prototypeUser2.destroy();
        for (Map.Entry<String, User> entry : this.users.entrySet()) {
            String beanName = entry.getKey();
            BeanDefinition beanDefinition = beanFactory.getBeanDefinition(beanName);
            if (beanDefinition.isPrototype()) {
                entry.getValue().destroy();
            }
        }
        System.out.println("demo destroyed...");
    }
}
