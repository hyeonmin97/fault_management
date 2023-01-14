package com.example.demo.domain;

import lombok.Getter;

import javax.persistence.*;

@Entity
@Getter
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String personId;
    private String password;
    private String name;
    private String email;
    private String phone;

    @Enumerated(EnumType.STRING)
    private EmployeeRole role;

}
