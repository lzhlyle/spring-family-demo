package com.lzhlyle.spring.demo.spring.bean.definition;

import com.lzhlyle.spring.demo.ioc.overview.domain.User;
import org.springframework.beans.MutablePropertyValues;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.GenericBeanDefinition;

public class BeanDefinitionCreationDemo {
    public static void main(String[] args) {

    }

    private static void createWithBeanDefinitionBuilder() {
        BeanDefinitionBuilder beanDefinitionBuilder = BeanDefinitionBuilder.genericBeanDefinition(User.class);
        beanDefinitionBuilder
                .addPropertyValue("id", 3L)
                .addPropertyValue("name", "zapup");
        BeanDefinition beanDefinition = beanDefinitionBuilder.getBeanDefinition(); // 并非最终状态，还可自定义修改
    }

    private static void createWithAbstractBeanDefinition() {
        GenericBeanDefinition beanDefinition = new GenericBeanDefinition();
        beanDefinition.setBeanClass(User.class);
        MutablePropertyValues mutablePropertyValues = new MutablePropertyValues();
//        mutablePropertyValues.addPropertyValue("id", 3L);
//        mutablePropertyValues.addPropertyValue("name", "zapup");
        mutablePropertyValues
                .add("id", 3L)
                .add("name", "zapup");
        beanDefinition.setPropertyValues(mutablePropertyValues);
    }
}
