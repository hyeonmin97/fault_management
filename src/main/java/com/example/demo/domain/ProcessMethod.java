package com.example.demo.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ProcessMethod {

    CALL("CALL", "유선처리"), VISIT("VISIT", "방문처리");

    private String type;
    private String value;
}
