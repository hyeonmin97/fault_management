package com.example.demo.domain;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.Builder;
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
	@JoinColumn(foreignKey=@ForeignKey(name="FK_PROCESSINGTYPE_STORELIST"))
	private StoreList store;
	
	//장애코드 외래키 연결
	@ManyToOne
	@JoinColumn(foreignKey=@ForeignKey(name="FK_PROCESSINGTYPE_DEFECTTYPE"))
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
	@JoinColumn(foreignKey=@ForeignKey(name="FK_PROCESSINGTYPE_ENGINEERLIST"))
	private EngineerList engineer;
	
	@Column
	//배정일자
	private LocalDateTime allocationDate;
	
	@Column
	//처리일자
	private LocalDateTime completionDate;
	
	//진행단계번호 외래키 연결
	@ManyToOne
	@JoinColumn(foreignKey=@ForeignKey(name="FK_PROCESSINGTYPE_PROGRESSIVESTEP"))
	private ProgressiveStep progressiveStep;
	
	@Column(length=20)
	//처리방법
	private String method;

	@Column(length=1)
	//예약점검 여부
	private String inspectionStatus;

	@Builder
	public ProcessingType(Long serialNumber, StoreList store, DefectType defect, String detailContent,
			String receptionist, String receptionistPhoneNumber, EngineerList engineer, LocalDateTime allocationDate,
			LocalDateTime completionDate, ProgressiveStep progressiveStep, String method, String inspectionStatus) {
		this.serialNumber = serialNumber;
		this.store = store;
		this.defect = defect;
		this.detailContent = detailContent;
		this.receptionist = receptionist;
		this.receptionistPhoneNumber = receptionistPhoneNumber;
		this.engineer = engineer;
		this.allocationDate = allocationDate;
		this.completionDate = completionDate;
		this.progressiveStep = progressiveStep;
		this.method = method;
		this.inspectionStatus = inspectionStatus;
	}
}
