package com.gk.mapper;

import com.gk.entity.Emp;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface EmpMapper{

    /*级联查询*/
    List<Emp> getEmpByDeptno(Integer deptno);

    Emp findByEmpno(Integer empno);
}
