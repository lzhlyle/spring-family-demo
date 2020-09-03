package com.lzhlyle.spring.demo.spring.bean.definition.factory;

import org.springframework.beans.factory.InitializingBean;

import javax.annotation.PostConstruct;

public class DefaultUserFactory implements IUserFactory, InitializingBean {
    @PostConstruct
    public void initByPostConstruct() {
        System.out.println("@PostConstruct: UserFactory Initializing...");
    }

    public void initByCustomWithAnnotation() {
        System.out.println("Custom Annotation: UserFactory Initializing...");
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("afterPropertiesSet: UserFactory Initializing...");
    }
}
