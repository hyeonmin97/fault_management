package com.example.demo.controller.dto;

import com.example.demo.domain.AgencyControl;
import com.example.demo.domain.StoreAgencyStatus;
import lombok.Builder;
import lombok.Getter;
import java.util.ArrayList;
import java.util.List;

@Getter
public class AgencyInfoDto {

    private String agencyCode;
    private String name;
    private String postalCode;
    private String address;
    private String addressDetail;
    private String telephone;
    private String fax;
    private String managerName;
    private String managerPhone;
    private StoreAgencyStatus status;

    @Builder.Default
    private List<AgencyControl> agencyControlList = new ArrayList<>();

    @Builder.Default
    private List<AgencyInfoEngineerDto> engineerList = new ArrayList<>();

    @Builder.Default
    private List<IncidentHistoryDto> incidentHistoryDtoList = new ArrayList<>();

    @Builder
    public AgencyInfoDto(String agencyCode, String name, String postalCode, String address, String addressDetail, String telephone, String fax, String managerName, String managerPhone, StoreAgencyStatus status) {
        this.agencyCode = agencyCode;
        this.name = name;
        this.postalCode = postalCode;
        this.address = address;
        this.addressDetail = addressDetail;
        this.telephone = telephone;
        this.fax = fax;
        this.managerName = managerName;
        this.managerPhone = managerPhone;
        this.status = status;
    }
}
