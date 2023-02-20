package com.example.demo.domain.searchType;

import lombok.Getter;

@Getter
public enum SearchTypeAgency {
    AGENCY_CODE("대리점 코드"), NAME("대리점명"), POSTAL_CODE("우편번호"), ADDRESS("주소");
    String value;

    SearchTypeAgency(String value) {
        this.value = value;
    }
}
