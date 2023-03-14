package com.example.demo.controller.dto;

import com.example.demo.domain.EngineerStatus;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@SuperBuilder
public class EngineerInfoDto extends EngineerDto{

    @Builder.Default
    private List<IncidentHistoryDto> incidentHistoryDtoList = new ArrayList<>();
}
