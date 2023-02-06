package com.example.demo.controller.dto;

import com.example.demo.domain.ProcessMethod;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class AddIncidentDto {
    private String storeCode;
    private Long incidentType;
    private ProcessMethod processMethod;
    private String text;
    private String agencyCode;
    private String customerName;
    private String customerPhone;

    @Builder
    public AddIncidentDto(String storeCode, Long incidentType, ProcessMethod processMethod, String text, String agencyCode, String customerName, String customerPhone) {
        this.storeCode = storeCode;
        this.incidentType = incidentType;
        this.processMethod = processMethod;
        this.text = text;
        this.agencyCode = agencyCode;
        this.customerName = customerName;
        this.customerPhone = customerPhone;
    }
}
