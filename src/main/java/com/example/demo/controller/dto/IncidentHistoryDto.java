package com.example.demo.controller.dto;

import com.example.demo.domain.EngineerStatus;
import com.example.demo.domain.IncidentType;
import com.example.demo.domain.ProcessMethod;
import com.example.demo.domain.ProcessStatus;
import lombok.*;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class IncidentHistoryDto {

    private Long incidentId;
    private LocalDateTime createDate;
    private LocalDateTime completionDate;
    private String text;
    private String agencyName;
    private String agencyCode;
    private String engineerName;
    private Long engineerId;
    private EngineerStatus engineerStatus;
    private ProcessMethod processMethod;
    private ProcessStatus processStatus;
    private IncidentType incidentType;
    private String customerName;
    private String customerPhone;
    private boolean isRegular;
    private Long employeeId;
    private String employeeName;

    @Builder
    public IncidentHistoryDto(Long incidentId, LocalDateTime createDate, LocalDateTime completionDate, String text, String agencyName, String agencyCode, String engineerName, Long engineerId, EngineerStatus engineerStatus, ProcessMethod processMethod, ProcessStatus processStatus, IncidentType incidentType, String customerName, String customerPhone, boolean isRegular, Long employeeId, String employeeName) {
        this.incidentId = incidentId;
        this.createDate = createDate;
        this.completionDate = completionDate;
        this.text = text;
        this.agencyName = agencyName;
        this.agencyCode = agencyCode;
        this.engineerName = engineerName;
        this.engineerId = engineerId;
        this.engineerStatus = engineerStatus;
        this.processMethod = processMethod;
        this.processStatus = processStatus;
        this.incidentType = incidentType;
        this.customerName = customerName;
        this.customerPhone = customerPhone;
        this.isRegular = isRegular;
        this.employeeId = employeeId;
        this.employeeName = employeeName;
    }
    public static class Test{
        private String test;

        public Test(String test) {
            this.test = test;
        }
    }

}
