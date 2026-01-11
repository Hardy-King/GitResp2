package com.gk;

import com.gk.entity.Emp;
import com.gk.mapper.EmpMapper;
import com.gk.mapper.EmpMapper2;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

public class Test04 {

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
        EmpMapper2 empMapper=sqlSession.getMapper(EmpMapper2.class);
        Emp emp=new Emp();
        List<Emp> list = empMapper.findByCondition(emp);
        for (Emp emps : list) {
            System.out.println(emps);
        }
    }

    @Test
    public void test02(){
        EmpMapper2 empMapper=sqlSession.getMapper(EmpMapper2.class);
        Emp emp=new Emp();
        /**
         * empno=7369, ename=SMITH
         */
        //emp.setEmpno(7369);
        emp.setEname("S");
        List<Emp> list = empMapper.findByCondition(emp);
        for (Emp emps : list) {
            System.out.println(emps);
        }
    }

    @Test
    public void test03() throws ParseException {
        EmpMapper2 empMapper=sqlSession.getMapper(EmpMapper2.class);
        Emp emp=new Emp();
        emp.setHiredate(new SimpleDateFormat("yyyy-MM-dd").parse("1982-01-23"));
        List<Emp> empList = empMapper.findByCondition(emp);
        for (Emp emps : empList) {
            System.out.println(emps);
        }
    }

    @Test
    public void test04() {
        EmpMapper2 empMapper=sqlSession.getMapper(EmpMapper2.class);
        Emp emp=new Emp();
        /**
         * empno=7369, ename=SMITH
         */
        //emp.setEmpno(7369);
        emp.setEname("S");
        List<Emp> list = empMapper.findByCondition(emp);
        for (Emp emps : list) {
            System.out.println(emps);
        }
    }

    @Test
    public void test05() {
        EmpMapper2 mapper = sqlSession.getMapper(EmpMapper2.class);
        List<Emp> empList = mapper.findByEmpno1(new int[]{7369, 7934, 8001});
        for (Emp emps : empList) {
            System.out.println(emps);
        }
    }
    @Test
    public void test06() {
        EmpMapper2 mapper = sqlSession.getMapper(EmpMapper2.class);
        List<Integer> integerList = new ArrayList<Integer>();
        /*integerList.add(7369);
        integerList.add(7934);
        integerList.add(8001);*/
        Collections.addAll(integerList,7369,7934,8001);
        List<Emp> empList = mapper.findByEmpno2(integerList);
        for (Emp emps : empList) {
            System.out.println(emps);
        }
    }

    @After
    public void release(){
        sqlSession.close();
    }
}
