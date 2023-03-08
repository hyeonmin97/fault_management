package com.example.demo.domain;

import lombok.Getter;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@ToString
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

    //엔티티가 dto를 참조하지 않도록 하기 위해 매개변수로 넣었음
    public void update(String name, String age, String phone, LocalDateTime joinDate, LocalDateTime resignationDate, EngineerStatus engineerStatus, Agency agency) {
        this.name = name;
        this.age = age;
        this.phone = phone;
        this.joinDate = joinDate;
        this.resignationDate = resignationDate;
        this.engineerStatus = engineerStatus;
        this.agency = agency;
    }
}
