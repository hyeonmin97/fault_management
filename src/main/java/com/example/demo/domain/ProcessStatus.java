package com.example.demo.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ProcessStatus {

    RECEIVE("RECEIVE", "접수"), PROCESS("PROCESS", "처리중"), DONE("DONE", "처리완료");
    private String type;
    private String value;
}
