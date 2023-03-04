package com.example.demo.controller.dto;

import com.example.demo.domain.EngineerStatus;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class EngineerInfoDto {

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
    @Builder.Default
    private List<IncidentHistoryDto> incidentHistoryDtoList = new ArrayList<>();

    @Builder
    public EngineerInfoDto(Long id, String name, String age, String phone, LocalDateTime createDate, LocalDateTime updateDate, LocalDateTime joinDate, LocalDateTime resignationDate, EngineerStatus engineerStatus, String agencyCode, String agencyName) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.phone = phone;
        this.createDate = createDate;
        this.updateDate = updateDate;
        this.joinDate = joinDate;
        this.resignationDate = resignationDate;
        this.engineerStatus = engineerStatus;
        this.agencyCode = agencyCode;
        this.agencyName = agencyName;
    }
}
