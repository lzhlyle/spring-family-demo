package com.lzhlyle.spring.demo.mybatis;

import com.lzhlyle.spring.demo.mybatis.mapper.EngNameMapper;
import com.lzhlyle.spring.demo.mybatis.model.EngName;
import lombok.extern.java.Log;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.Reader;
import java.util.List;

@Log
public class MyBatisAppStart {
    public static void main(String[] args) throws IOException {
        try (Reader reader = Resources.getResourceAsReader("mybatis-config.xml")) {
            SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(reader);
            List<EngName> list = null;
            try (SqlSession sqlSession = factory.openSession()) {
//                list = sqlSession.selectList("selectAll");
                EngNameMapper mapper = sqlSession.getMapper(EngNameMapper.class);
                list = mapper.selectAll();
            }

            if (list == null) log.warning("not query");
            else {
                for (EngName engName : list) {
                    log.info(engName.toString());
                }
            }
        }
    }
}
