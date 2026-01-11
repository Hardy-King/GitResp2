package com.gk.mapper;

import com.gk.entity.Emp;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface EmpMapper2 {

    List<Emp> findByCondition(Emp emp);
    List<Emp> findByCondition2(Emp emp);


    List<Emp> findByEmpno1(int[] empnos);
    List<Emp> findByEmpno2(List<Integer> empnos);
}
