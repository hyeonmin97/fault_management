package com.example.demo.controller.dto;

import com.example.demo.domain.EngineerStatus;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@ToString
public class PatchEngineerForm {
    private Long id;
    private String name;
    private String age;
    private String phone;
    private LocalDateTime joinDate;
    private LocalDateTime resignationDate;
    private EngineerStatus engineerStatus;
    private String agencyCode;

    @Builder
    public PatchEngineerForm(Long id, String name, String age, String phone, LocalDateTime joinDate, LocalDateTime resignationDate, EngineerStatus engineerStatus, String agencyCode) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.phone = phone;
        this.joinDate = joinDate;
        this.resignationDate = resignationDate;
        this.engineerStatus = engineerStatus;
        this.agencyCode = agencyCode;
    }
}
