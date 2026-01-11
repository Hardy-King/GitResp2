package com.gk;

import com.gk.entity.Emp;
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

    @Test
    public void test01(){
        /**
         * getMapper 基于接口的代理
         */
        EmpMapper mapper = sqlSession.getMapper(EmpMapper.class);
        List<Emp> list = mapper.findAll();
        list.forEach(System.out::println);
    }

    @Test
    public void test02(){
        EmpMapper mapper = sqlSession.getMapper(EmpMapper.class);
        Emp emp = mapper.findByEmpNo(7369);
        System.out.println(emp);
    }

    @Test
    public void test03(){

        EmpMapper mapper = sqlSession.getMapper(EmpMapper.class);
        List<Emp> list = mapper.findByDeptAndSal(10, 2000.00);
        list.forEach(System.out::println);
    }

    /*
    * 参数是Map
    * */
    @Test
    public void test04(){
        EmpMapper mapper = sqlSession.getMapper(EmpMapper.class);
        Map<String,Object> args = new HashMap<>();
        args.put("deptno",10);
        args.put("sal",2000.00);
        List<Emp> empList = mapper.findByDeptAndSal2(args);
        empList.forEach(System.out::println);
    }

    /**
     * 参数是对象类型Emp
     */
    @Test
    public void test05(){
        EmpMapper mapper = sqlSession.getMapper(EmpMapper.class);
        Emp emp = new Emp();
        emp.setDeptno(10);
        emp.setSal(2000.00);
        List<Emp> emps = mapper.findByDeptAndSal3(emp);
        emps.forEach(System.out::println);
    }

    @Test
    public void test06(){
        EmpMapper mapper = sqlSession.getMapper(EmpMapper.class);
        Emp empa = new Emp();
        Emp empb = new Emp();
        empa.setDeptno(10);
        empb.setSal(2000.00);
        List<Emp> emps = mapper.findByDeptAndSal4(empa, empb);  //多态
        emps.forEach(System.out::println);
    }

    @Test
    public void test07(){
        EmpMapper mapper = sqlSession.getMapper(EmpMapper.class);
        List<Emp> emps = mapper.findByDeptAndSal5("O");
        emps.forEach(System.out::println);
    }

    @After
    public void release(){
        sqlSession.close();
    }
}
