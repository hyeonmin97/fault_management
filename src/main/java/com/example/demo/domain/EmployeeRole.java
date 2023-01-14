package com.example.demo.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum EmployeeRole {
    ADMIN("ADMIN", "관리자"), CENTER("CENTER", "센터"), AGENCY("AGENCY", "대리점");

    private String type;
    private String value;
}
