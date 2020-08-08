package com.lzhlyle.spring.demo.mybatis.mapper;

import com.lzhlyle.spring.demo.mybatis.model.EngName;

import java.util.List;

public interface EngNameMapper {
    List<EngName> selectAll();
}
