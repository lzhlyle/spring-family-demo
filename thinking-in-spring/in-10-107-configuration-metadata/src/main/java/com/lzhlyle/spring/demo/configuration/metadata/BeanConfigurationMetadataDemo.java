package com.lzhlyle.spring.demo.configuration.metadata;

import com.lzhlyle.spring.demo.ioc.overview.domain.User;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.util.ObjectUtils;

public class BeanConfigurationMetadataDemo {
    public static void main(String[] args) {
        BeanDefinitionBuilder builder = BeanDefinitionBuilder.genericBeanDefinition(User.class);
        builder.addPropertyValue("name", "lzh");

        AbstractBeanDefinition beanDefinition = builder.getBeanDefinition();
        // 附加属性，辅助作用，不影响 bean
        beanDefinition.setAttribute("name", "who");
        // 指定来源，辅助作用
        beanDefinition.setSource(BeanConfigurationMetadataDemo.class);

        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        beanFactory.addBeanPostProcessor(new BeanPostProcessor() {
            // 初始化后
            @Override
            public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
                if (ObjectUtils.nullSafeEquals("user", beanName) && User.class.equals(bean.getClass())) {
                    BeanDefinition beanDefinition = beanFactory.getBeanDefinition(beanName);
                    String name = (String) beanDefinition.getAttribute("name"); // who
                    System.out.println(name);
//                    User user = (User) bean;
//                    user.setName(name);

                    Object source = beanDefinition.getSource();
                    System.out.println(source);

                }
                return bean;
            }
        });
        // register
        beanFactory.registerBeanDefinition("user", beanDefinition);
        // lookup
        User user = (User) beanFactory.getBean("user", User.class);
        System.out.println(user);
    }
}
