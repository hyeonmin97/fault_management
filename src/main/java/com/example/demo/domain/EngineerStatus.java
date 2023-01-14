package com.example.demo.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum EngineerStatus {
    WORK("WORK", "재직중"), RESIGNATION("RESIGNATION", "퇴사");

    private String type;
    private String value;
}
