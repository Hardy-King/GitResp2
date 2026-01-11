package com.gk.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProjectRecord {
    private Integer empno;
    private Integer pid;

    // 组合一个Emp对象作为属性
    private Emp emp;

}
