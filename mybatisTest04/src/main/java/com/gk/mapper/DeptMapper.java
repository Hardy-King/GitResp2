package com.gk.mapper;

import com.gk.entity.Dept;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

public interface DeptMapper {
    Dept getDeptById(int id);

    @Select("select * from dept where deptno=#{deptno}")
    Dept findDeptById(int id);

    @Update("update dept set dname=#{dname}, loc=#{loc} where deptno = #{deptno}")
    int updateDept(Dept dept);

    @Update("insert into dept values (default,#{dname},#{loc})")
    int addDept(Dept dept);

    @Delete("delete from dept where deptno = #{deptno}")
    int removeDeptById(int deptno);
}
