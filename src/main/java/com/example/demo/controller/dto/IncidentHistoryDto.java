package com.example.demo.controller.dto;

import com.example.demo.domain.IncidentType;
import com.example.demo.domain.ProcessStatus;
import lombok.*;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class IncidentHistoryDto {

    private LocalDateTime createDate;
    private LocalDateTime completionDate;
    private String text;
    private String agencyName;
    private String agencyCode;
    private String engineerName;
    private Long engineerId;
    private ProcessStatus processStatus;
    private IncidentType incidentType;

    @Builder
    public IncidentHistoryDto(LocalDateTime createDate, LocalDateTime completionDate, String text, String agencyName, String agencyCode, String engineerName, Long engineerId, ProcessStatus processStatus, IncidentType incidentType) {
        this.createDate = createDate;
        this.completionDate = completionDate;
        this.text = text;
        this.agencyName = agencyName;
        this.agencyCode = agencyCode;
        this.engineerName = engineerName;
        this.engineerId = engineerId;
        this.processStatus = processStatus;
        this.incidentType = incidentType;
    }
}
