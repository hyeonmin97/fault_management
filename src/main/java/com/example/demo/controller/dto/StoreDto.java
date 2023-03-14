package com.example.demo.controller.dto;

import com.example.demo.domain.Location;
import com.example.demo.domain.Store;
import com.example.demo.domain.StoreAgencyStatus;
import com.example.demo.domain.StoreType;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.locationtech.jts.geom.Point;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
public class StoreDto {
    private String name;
    private String telephone;
    private String fax;
    private String managerName;
    private String managerPhone;
    private String postalCode;
    private String address;
    private String addressDetail;
    private String storeCode;
    private LocalDateTime createDate;
    private LocalDateTime updateDate;
    private LocalDateTime openDate;
    private LocalDateTime closeDate;
    private LocalDateTime inspectionDateLast;
    private LocalDateTime inspectionDateNext;
    private StoreType storeType;
    private StoreAgencyStatus storeAgencyStatus;
    private Location location;


    public StoreDto(Store store) {
        this.name = store.getName();
        this.telephone = store.getTelephone();
        this.fax = store.getFax();
        this.managerName = store.getManagerName();
        this.managerPhone = store.getManagerPhone();
        this.postalCode = store.getPostalCode();
        this.address = store.getAddress();
        this.addressDetail = store.getAddressDetail();
        this.storeCode = store.getStoreCode();
        this.createDate = store.getCreateDate();
        this.updateDate = store.getUpdateDate();
        this.openDate = store.getOpenDate();
        this.closeDate = store.getCloseDate();
        this.inspectionDateLast = store.getInspectionDateLast();
        this.inspectionDateNext = store.getInspectionDateNext();
        this.storeType = store.getStoreType();
        this.storeAgencyStatus = store.getStoreAgencyStatus();
        this.location = store.getLocation();
    }
}
