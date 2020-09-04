package com.lzhlyle.spring.demo.spring.bean.definition.factory;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.xml.transform.Source;

public class DefaultUserFactory implements IUserFactory, InitializingBean, DisposableBean {
    @PostConstruct
    public void initByPostConstruct() {
        System.out.println("@PostConstruct: UserFactory Initializing...");
    }

    public void initByCustom() {
        System.out.println("Custom init: UserFactory Initializing...");
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("InitializingBean.afterPropertiesSet(): UserFactory Initializing...");
    }

    @PreDestroy
    public void destroyByPreDestroy() {
        System.out.println("@PreDestroy: UserFactory Destroying...");
    }

    @Override
    public void destroy() throws Exception {
        System.out.println("DisposableBean.destroy(): UserFactory Destroying...");
    }

    public void destroyByCustom() {
        System.out.println("Custom destroy: UserFactory Destroying...");
    }

    @Override
    protected void finalize() throws Throwable {
        System.out.println("UserFactory finalizing...");
    }
}
