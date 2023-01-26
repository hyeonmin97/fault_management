package com.example.demo.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;
import org.locationtech.jts.geom.Point;

import javax.persistence.*;

@Entity
@Getter
@ToString
@SuperBuilder
@NoArgsConstructor
public class Agency extends StoreAgencySuperEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String agencyCode;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private StoreAgencyStatus storeAgencyStatus;

    @Column(columnDefinition = "point")
    private Point point;
}
