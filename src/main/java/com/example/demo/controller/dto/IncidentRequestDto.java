package com.example.demo.controller.dto;

import com.example.demo.domain.IncidentType;
import lombok.*;
import lombok.extern.slf4j.Slf4j;
import java.util.List;

@Getter
@Slf4j
@NoArgsConstructor
@ToString
public class IncidentRequestDto {
    private String storeCode;
    private String storeName;
    private String storeTelephone;
    private String storeAddress;
    private String StoreAddressDetail;
    private String managerName;
    private String managerPhone;
    private List<AgencyAndStoreDistanceDto> agencyAndStoreDistanceDtoList; // 점포-대리점 거리, 대리점 선택을 위한 데이터
    private List<IncidentHistoryDto>  incidentHistoryLIst; //과거 장애 내역

    @Builder
    public IncidentRequestDto(String storeCode, String storeName, String storeTelephone, String storeAddress, String storeAddressDetail, String managerName, String managerPhone, List<AgencyAndStoreDistanceDto> agencyAndStoreDistanceDtoList, List<IncidentHistoryDto> incidentHistoryLIst) {
        this.storeCode = storeCode;
        this.storeName = storeName;
        this.storeTelephone = storeTelephone;
        this.storeAddress = storeAddress;
        this.StoreAddressDetail = storeAddressDetail;
        this.managerName = managerName;
        this.managerPhone = managerPhone;
        this.agencyAndStoreDistanceDtoList = agencyAndStoreDistanceDtoList;
        this.incidentHistoryLIst = incidentHistoryLIst;
    }
}
