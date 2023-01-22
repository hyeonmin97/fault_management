package com.example.demo.repository;

import lombok.Getter;

@Getter
public enum SearchType {
    StoreCode("점포코드"), Name("점포명"), Telephone("매장 전화번호"), ManagerName("사장님 이름"), ManagerPhone("사장님 전화번호");
    String value;

    SearchType(String value) {
        this.value = value;
    }
}
