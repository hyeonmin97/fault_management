package com.example.demo.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor
public class Store extends StoreAgencySuperEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String storeCode;


    private LocalDateTime inspectionDataLast;
    private LocalDateTime inspectionDateNext;

    @Enumerated(EnumType.STRING)
    @Column(name = "type")
    private StoreType storeType;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private StoreAgencyStatus storeAgencyStatus;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "location_id")
    private Location location;
}
