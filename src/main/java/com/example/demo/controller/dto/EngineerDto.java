package com.example.demo.controller.dto;

import com.example.demo.domain.EngineerStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

@Getter
@SuperBuilder
public class EngineerDto {

    private Long id;
    private String name;
    private String age;
    private String phone;
    private LocalDateTime createDate;
    private LocalDateTime updateDate;
    private LocalDateTime joinDate;
    private LocalDateTime resignationDate;
    private EngineerStatus engineerStatus;
    private String agencyCode;
    private String agencyName;
}
