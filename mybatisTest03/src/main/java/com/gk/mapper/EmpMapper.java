package com.gk.mapper;

import com.gk.entity.Dept;
import com.gk.entity.Emp;
import com.gk.entity.Project;

import java.util.List;

public interface EmpMapper {

    List<Emp> findEmpAndDeptByEmpId(Integer empId);

    Dept findDeptAndEmpByDeptId(Integer deptId);

    /**
     * 根据项目编号查询一个项目信息及参与该项目的所有员工信息
     * @param pid 项目编号
     * @return 所有信息封装的Project对象
     */
    Project findProjectJoinEmpsByPid(int pid);

}
