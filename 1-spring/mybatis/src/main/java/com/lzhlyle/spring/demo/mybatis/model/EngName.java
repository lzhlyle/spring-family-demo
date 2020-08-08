package com.lzhlyle.spring.demo.mybatis.model;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class EngName {
    private Integer id;
    private String firstName;
    private String lastName;
    private Integer age;
    private BigDecimal amount;
}
