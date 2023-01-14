package com.example.demo.domain;

import lombok.Getter;

import javax.persistence.*;

@Entity
@Getter
public class AgencyControl {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "location_id")
    private Location location;

    @ManyToOne
    @JoinColumn(name = "agency_id")
    private Agency agency;
}
