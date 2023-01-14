package com.example.demo.domain;

import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@MappedSuperclass
public abstract class StoreAgencySuperEntity extends BaseTimeEntity{

    private LocalDateTime openDate;
    private LocalDateTime closeDate;
    private String name;
    private String telephone;
    private String fax;
    private String managerName;
    private String managerPhone;
    private String postalCode;
    private String address;
    private String addressDetail;



}
