package com.example.demo.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum StoreAgencyStatus {
    OPEN("OPEN", "영업중"), CLOSED("CLOSED", "폐업"), CONSTRUCTION("CONSTRUCTION", "공사중");

    private String type;
    private String value;
}
