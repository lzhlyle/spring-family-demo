package com.lzhlyle.spring.demo.spring.bean.definition.factory;

import com.lzhlyle.spring.demo.ioc.overview.domain.User;
import org.springframework.beans.factory.FactoryBean;

public class UserFactoryBean implements FactoryBean<User> {
    @Override
    public User getObject() throws Exception {
        User user = new User();
        user.setId(6L);
        user.setName("user-factory-bean");
        return user;
    }

    @Override
    public Class<?> getObjectType() {
        return User.class;
    }
}
