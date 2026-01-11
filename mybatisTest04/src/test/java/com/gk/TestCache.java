package com.gk;

import com.gk.entity.Emp;
import com.gk.mapper.EmpMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;

public class TestCache {

    private SqlSession sqlSession;
    private SqlSessionFactory factory;
    @Before
    public void init(){
        SqlSessionFactoryBuilder ssfb =new SqlSessionFactoryBuilder();
        InputStream resourceAsStream = null;
        try {
            resourceAsStream = Resources.getResourceAsStream("sqlMapConfig.xml");
        } catch (IOException e) {
            e.printStackTrace();
        }
        factory = ssfb.build(resourceAsStream) ;
        sqlSession=factory.openSession();
    }

    /**
     * 一级缓存存在于sqlSession中，默认开启
     */
    @Test
    public void test01(){
        EmpMapper mapper = sqlSession.getMapper(EmpMapper.class);
        Emp emp = mapper.findByEmpno(7499);
        System.out.println(emp);

        //sqlSession.commit();// 中间发生了增删改或者是调用了sqlsession调用了commit，会自动清空缓存

        EmpMapper mapper2 = sqlSession.getMapper(EmpMapper.class);
        Emp emp2 = mapper2.findByEmpno(7499);
        System.out.println(emp2);

        System.out.println(emp == emp2);
    }

    /**
     * 二级缓存是夸sqlsession，存在于namespace，默认不开启
     * 1. <setting name="cacheEnabled" value="true"/>  sqlMapConfig
     * 2. namespace中<cache/>
     * 3. select中 useCache="true"
     */
    @Test
    public void test02(){
        SqlSession sqlSession1 ;
        sqlSession1 = factory.openSession();
        EmpMapper mapper = sqlSession1.getMapper(EmpMapper.class);
        Emp emp = mapper.findByEmpno(7499);
        System.out.println(emp);

        sqlSession1.commit();//提交缓存

        SqlSession sqlSession2 ;
        sqlSession2 = factory.openSession();
        EmpMapper mapper2 = sqlSession2.getMapper(EmpMapper.class);
        Emp emp2 = mapper2.findByEmpno(7499);
        System.out.println(emp2);

        System.out.println(emp == emp2);
    }
}
