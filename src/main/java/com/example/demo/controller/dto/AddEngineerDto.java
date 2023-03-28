package com.example.demo.controller.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Getter
@Setter
@ToString
public class AddEngineerDto {
    private String name;
    private int age;
    private String phone;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate joinDate;
    private Long agencyId;

}
