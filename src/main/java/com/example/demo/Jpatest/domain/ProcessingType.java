package com.example.demo.Jpatest.domain;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Entity
public class ProcessingType extends BaseTimeEntity{
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	//일련번호
	private Long serialNumber;
	
	//점포코드 외래키 연결
	@ManyToOne
	private StoreList store;
	
	//장애코드 외래키 연결
	@ManyToOne
	private DefectType defect;
	
	@Column(length = 60)
	//상세내용
	private String detailContent;
	
	@Column(length = 30)
	//접수자이름
	private String receptionist;
	
	@Column(length = 20)
	//접수자전화번호
	private String receptionistPhoneNumber;
	
	//접수일자는 자동으로 생성(BaseTimeEntity)
	
	//엔지니어 외래키 연결
	@ManyToOne
	private EngineerList engineer;
	
	@Column
	//배정일자
	private LocalDateTime allocationDate;
	
	@Column
	//처리일자
	private LocalDateTime completionDate;
	
	//진행단계번호 외래키 연결
	@ManyToOne
	private ProgressiveStep progressiveSteop;
	
	@Column(length=20)
	//처리방법
	private String method;

	@Column(length=1)
	//처리방법
	private String inspectionStatus;
}
