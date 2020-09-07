package com.lzhlyle.spring.demo.dependency.injection.annotation;

import org.springframework.core.annotation.Order;

import java.lang.annotation.*;

@Target({ElementType.CONSTRUCTOR, ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface InjectedUser {
}
