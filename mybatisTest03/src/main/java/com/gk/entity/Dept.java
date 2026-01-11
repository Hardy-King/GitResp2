package com.gk.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Dept {

    private Integer deptno;
    private String dname;
    private String loc;

    private List<Emp> empList;

}
