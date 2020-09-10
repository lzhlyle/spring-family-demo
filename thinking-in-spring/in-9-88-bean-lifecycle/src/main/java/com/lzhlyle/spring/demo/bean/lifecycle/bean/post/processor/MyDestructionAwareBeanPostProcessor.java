package com.lzhlyle.spring.demo.bean.lifecycle.bean.post.processor;

import com.lzhlyle.spring.demo.bean.lifecycle.domain.UserHolder;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.DestructionAwareBeanPostProcessor;
import org.springframework.util.ObjectUtils;

public class MyDestructionAwareBeanPostProcessor implements DestructionAwareBeanPostProcessor {
    @Override
    public void postProcessBeforeDestruction(Object bean, String beanName) throws BeansException {
        if (ObjectUtils.nullSafeEquals("userHolder", beanName) && UserHolder.class.equals(bean.getClass())) {
            UserHolder userHolder = (UserHolder) bean;
            userHolder.setDescription("from postProcessBeforeDestruction");
            System.out.println(userHolder.getDescription());
        }
    }
}
