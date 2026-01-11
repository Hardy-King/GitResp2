package com.gk.entity;

import lombok.Data;

/**
 * git测试类
 */
@Data
public class Person {

    int id;
    String name;
    String address;

    public Person(String name, String address) {
        this.name = name;
        this.address = address;
    }
}

