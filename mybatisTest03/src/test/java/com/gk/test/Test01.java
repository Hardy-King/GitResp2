package com.gk.test;

import com.gk.entity.Dept;
import com.gk.entity.Emp;
import com.gk.entity.Project;
import com.gk.mapper.EmpMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.jdbc.SQL;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;


public class Test01 {

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

    /**
     * 一对一
     */
    @Test
    public void test01(){
        EmpMapper mapper = sqlSession.getMapper(EmpMapper.class);
        List<Emp> emps = mapper.findEmpAndDeptByEmpId(7499);
        emps.forEach(System.out::println);
    }

    /**
     * 一对多
     */
    @Test
    public void test02(){
        EmpMapper mapper = sqlSession.getMapper(EmpMapper.class);
        Dept dept = mapper.findDeptAndEmpByDeptId(20);
        System.out.println(dept);
        List<Emp> empList = dept.getEmpList();
        empList.forEach(System.out::println);

    }

    /**
     * 多对多
     */
    @Test
    public void test03(){
        EmpMapper mapper = sqlSession.getMapper(EmpMapper.class);
        Project project = mapper.findProjectJoinEmpsByPid(1);
        System.out.println(project);
    }
    @After
    public void release(){
        sqlSession.close();
    }
}
