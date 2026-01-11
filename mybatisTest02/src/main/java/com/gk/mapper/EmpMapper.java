package com.gk.mapper;

import com.gk.entity.Emp;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface EmpMapper {

    List<Emp> findAll();

    Emp findByEmpNo(Integer empno);

    List<Emp> findByDeptAndSal(@Param("a") Integer deptno, @Param("b") Double sal);
    List<Emp> findByDeptAndSal2(Map<String, Object> map);
    List<Emp> findByDeptAndSal3(Emp emp);

    List<Emp> findByDeptAndSal4(Emp empa, Emp empb);

    /**
     * 模糊查询
     * @param ename
     * @return
     */
    List<Emp> findByDeptAndSal5(String ename);

    /**
     * 添加员工
     * @param emp
     * @return
     */
    int addEmp(Emp emp);

    /**
     * 根据empno更新Emp
     * @param empno
     * @param ename
     * @return
     */
    int updateEmpByEmpno(@Param("empno") int empno, @Param("ename") String ename);

    int deleteEmpByEmpno(int empno);
}
