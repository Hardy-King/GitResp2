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
import java.util.Date;

public class Test03 {

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
    public void addEmpTest(){
        EmpMapper mapper = sqlSession.getMapper(EmpMapper.class);
        Emp emp = new Emp(8002, "TOM", "SALESMAN", 7521,new Date() , 2314.0, 100.0, 10);
        int count = mapper.addEmp(emp);
        sqlSession.commit();
        System.out.println(count);
    }

    @Test
    public void updateEmpTest(){
        EmpMapper mapper = sqlSession.getMapper(EmpMapper.class);
        int rows = mapper.updateEmpByEmpno(8001, "老张");
        System.out.println(rows);
        sqlSession.commit();
    }

    @Test
    public void deleteEmpTest(){
        EmpMapper mapper = sqlSession.getMapper(EmpMapper.class);
        int rows = mapper.deleteEmpByEmpno(8002);
        System.out.println(rows);
        sqlSession.commit();
    }

    @After
    public void release(){
        sqlSession.close();
    }
}
