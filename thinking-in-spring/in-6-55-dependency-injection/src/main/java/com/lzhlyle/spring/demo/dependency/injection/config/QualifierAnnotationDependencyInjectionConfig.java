package com.lzhlyle.spring.demo.dependency.injection.config;

import com.lzhlyle.spring.demo.dependency.injection.annotation.UserGroup;
import com.lzhlyle.spring.demo.ioc.overview.domain.User;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;

public class QualifierAnnotationDependencyInjectionConfig {
    @Bean
    @Qualifier // 分组
    public User user1() {
        return createUser(111L);
    }

    @Bean
    @Qualifier // 分组
    public User user2() {
        return createUser(222L);
    }

    @Bean
    @Qualifier("group3") // 分组
    public User user3() {
        return createUser(333L);
    }

    @Bean
    @UserGroup
    public User user4() {
        return createUser(444L);
    }

    private static User createUser(Long id) {
        return new User() {{
            setId(id);
        }};
    }
}
