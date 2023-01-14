package com.example.demo.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum StoreType {

    FRANCHISE("FRANCHISE", "프랜차이즈"), PERSONAL("PERSONAL", "자영업");

    private String type;
    private String value;
}
