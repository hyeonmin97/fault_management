package com.example.demo.controller.dto;

import com.example.demo.domain.StoreAgencyStatus;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@Builder
@ToString
public class AgencyAndStoreDistanceDto {

    private String agencyCode;
    private String name;
    private Double distance;
    private Integer totalCount;
    private Integer workCount;
    private StoreAgencyStatus agencyStatus;
}
