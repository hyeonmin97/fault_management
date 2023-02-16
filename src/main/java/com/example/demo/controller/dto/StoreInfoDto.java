package com.example.demo.controller.dto;

import com.example.demo.domain.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.locationtech.jts.geom.Point;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@ToString
public class StoreInfoDto {
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
    private Point point;
    private List<IncidentHistoryDto> incidentList;

    public StoreInfoDto(Store store, List<IncidentHistoryDto> list) {
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
        this.point = store.getPoint();
        this.incidentList = list;
    }
}
