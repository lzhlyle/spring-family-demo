package com.lzhlyle.spring.demo.spring.bean.definition.factory;

import com.lzhlyle.spring.demo.ioc.overview.domain.User;

public interface IUserFactory {
    default User createUser() {
        User user = new User();
        user.setId(5L);
        user.setName("factory-user");
        return user;
    }
}
