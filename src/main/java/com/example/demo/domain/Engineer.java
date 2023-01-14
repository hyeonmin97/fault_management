package com.example.demo.domain;

import lombok.Getter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
public class Engineer extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String age;
    private String phone;
    private LocalDateTime joinDate;
    private LocalDateTime resignationDate;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private EngineerStatus engineerStatus;

    @ManyToOne
    @JoinColumn(name = "agency_id")
    private Agency agency;
}
