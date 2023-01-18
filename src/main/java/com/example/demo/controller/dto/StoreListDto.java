package com.example.demo.controller.dto;

import com.example.demo.domain.Store;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StoreListDto {

    private String storeCode;
    private String headOfficeCode;
    private String name;
    private String telephone;
    private String postal_code;
    private String address;
    private String addressDetail;
    private String managerName;
    private String managerPhone;

    @Builder
    public StoreListDto(String storeCode, String headOfficeCode, String name, String telephone, String postal_code, String address, String addressDetail, String managerName, String managerPhone) {
        this.storeCode = storeCode;
        this.headOfficeCode = headOfficeCode;
        this.name = name;
        this.telephone = telephone;
        this.postal_code = postal_code;
        this.address = address;
        this.addressDetail = addressDetail;
        this.managerName = managerName;
        this.managerPhone = managerPhone;
    }

    public static StoreListDto of(Store store) {
        return StoreListDto.builder()
                .storeCode(store.getStoreCode())
                .headOfficeCode(store.getLocation().getHeadOfficeCode())
                .name(store.getName())
                .telephone(store.getTelephone())
                .postal_code(store.getPostalCode())
                .address(store.getAddress())
                .addressDetail(store.getAddressDetail())
                .managerName(store.getManagerName())
                .managerPhone(store.getManagerPhone())
                .build();
    }
}
