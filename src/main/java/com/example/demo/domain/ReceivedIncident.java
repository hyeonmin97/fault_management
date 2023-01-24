package com.example.demo.domain;

import lombok.Getter;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@ToString
public class ReceivedIncident extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime receptionDate;
    private LocalDateTime completionDate;

    @Enumerated(EnumType.STRING)
    @Column(name = "method")
    private ProcessMethod processMethod;

    @ManyToOne
    @JoinColumn(name = "incident_type_id")
    private IncidentType incidentType;


    private String customerName;
    private String customerPhone;
    private String text;

    @ManyToOne
    @JoinColumn(name = "store_id")
    private Store store;

    @ManyToOne
    @JoinColumn(name = "agency_id")
    private Agency agency;

    @ManyToOne
    @JoinColumn(name = "engineer_id")
    private Engineer engineer;
    private LocalDateTime engineerAssignmentDate;

    @ManyToOne
    @JoinColumn(name = "employee_id")
    private Employee employee;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private ProcessStatus processStatus;

    private Boolean isRegular;



}
