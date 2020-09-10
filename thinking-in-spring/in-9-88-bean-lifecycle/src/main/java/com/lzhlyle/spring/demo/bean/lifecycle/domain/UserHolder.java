package com.lzhlyle.spring.demo.bean.lifecycle.domain;

import com.lzhlyle.spring.demo.ioc.overview.domain.User;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.*;
import org.springframework.context.EnvironmentAware;
import org.springframework.core.env.Environment;

import javax.annotation.PostConstruct;
import javax.xml.transform.Source;

public class UserHolder implements
        BeanNameAware,
        BeanClassLoaderAware,
        BeanFactoryAware,
        EnvironmentAware,
        InitializingBean {
    private final User user;
    private Integer number;
    private String description;

    private ClassLoader classLoader;
    private BeanFactory beanFactory;
    private String beanName;
    private Environment enviroment;

    @PostConstruct
    public void initPostConstruct() {
        this.description = "from @PostConstruct";
        System.out.println(this.description);
    }

    @Override
    public void afterPropertiesSet() {
        this.description = "from afterPropertiesSet";
        System.out.println(this.description);
    }

    public void init() {
        this.description = "from init";
        System.out.println(this.description);
    }

    public UserHolder(User user) {
        this.user = user;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public User getUser() {
        return user;
    }

    public ClassLoader getClassLoader() {
        return classLoader;
    }

    public void setClassLoader(ClassLoader classLoader) {
        this.classLoader = classLoader;
    }

    public BeanFactory getBeanFactory() {
        return beanFactory;
    }

    public String getBeanName() {
        return beanName;
    }

    public Environment getEnviroment() {
        return enviroment;
    }

    public void setEnviroment(Environment enviroment) {
        this.enviroment = enviroment;
    }

    @Override
    public String toString() {
        return "UserHolder{" +
                "user=" + user +
                ", number=" + number +
                ", description='" + description + '\'' +
                ", beanName='" + beanName + '\'' +
                '}';
    }

    @Override
    public void setBeanName(String name) {
        this.beanName = name;
    }

    @Override
    public void setBeanClassLoader(ClassLoader classLoader) {
        this.classLoader = classLoader;
    }

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        this.beanFactory = beanFactory;
    }

    @Override
    public void setEnvironment(Environment environment) {
        this.enviroment = environment;
    }


}
