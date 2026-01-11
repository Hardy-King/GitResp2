package com.gk;

import com.gk.entity.Dept;
import com.gk.entity.Emp;
import com.gk.mapper.DeptMapper;
import com.gk.mapper.EmpMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Test02 {

    private SqlSession sqlSession;
    @Before
    public void init(){
        SqlSessionFactoryBuilder ssfb =new SqlSessionFactoryBuilder();
        InputStream resourceAsStream = null;
        try {
            resourceAsStream = Resources.getResourceAsStream("sqlMapConfig.xml");
        } catch (IOException e) {
            e.printStackTrace();
        }
        SqlSessionFactory factory=ssfb.build(resourceAsStream) ;
        sqlSession=factory.openSession();
    }

    @Test
    public void addDeptTest(){
        DeptMapper mapper = sqlSession.getMapper(DeptMapper.class);
        Dept dept = new Dept(null, "C++", "BEIJING");
        System.out.println(dept.getDeptno());
        int count = mapper.addDept(dept);
        sqlSession.commit();
        System.out.println(dept.getDeptno());
    }

    @Test
    public void addDeptTest2(){
        DeptMapper mapper = sqlSession.getMapper(DeptMapper.class);
        Dept dept = new Dept(null, "PHP", "BEIJING");
        System.out.println(dept.getDeptno());
        int count = mapper.addDept2(dept);
        sqlSession.commit();
        System.out.println(dept.getDeptno());
    }

    @After
    public void release(){
        sqlSession.close();
    }
}
