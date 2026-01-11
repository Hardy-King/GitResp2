package com.gk;

import com.gk.entity.Dept;
import com.gk.entity.Emp;
import com.gk.mapper.DeptMapper;
import com.gk.mapper.EmpMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class Test01 {
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

    @Test
    public void test(){
        //级联查询 懒加载 及时加载
        DeptMapper deptMapper = sqlSession.getMapper(DeptMapper.class);
        Dept dept = deptMapper.getDeptById(20);
        System.out.println(dept.getDname());
        //dept.getEmpList().forEach(System.out::println);
    }

// 注解 curd

    @Test
    public void testAddDept(){
        DeptMapper deptMapper = sqlSession.getMapper(DeptMapper.class);
        int i = deptMapper.addDept(new Dept(null, "总部", "北京"));
        System.out.println(i);
        sqlSession.commit();
    }
    @Test
    public void testUpdateDept(){
        DeptMapper deptMapper = sqlSession.getMapper(DeptMapper.class);
        int rows = deptMapper.updateDept(new Dept(47,"C","北京悦城"));
        System.out.println(rows);
        sqlSession.commit();
    }

}
