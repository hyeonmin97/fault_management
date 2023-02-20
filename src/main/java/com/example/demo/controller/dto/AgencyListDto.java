package com.example.demo.controller.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AgencyListDto {
    private String agencyCode;
    private String name;
    private String postalCode;
    private String address;
    private String addressDetail;
    private String telephone;
    private String fax;

    @Builder
    public AgencyListDto(String agencyCode, String name, String postalCode, String address, String addressDetail, String telephone, String fax) {
        this.agencyCode = agencyCode;
        this.name = name;
        this.postalCode = postalCode;
        this.address = address;
        this.addressDetail = addressDetail;
        this.telephone = telephone;
        this.fax = fax;
    }
}
