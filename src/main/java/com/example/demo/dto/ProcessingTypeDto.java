package com.example.demo.dto;

import java.time.LocalDateTime;

import com.example.demo.domain.DefectType;
import com.example.demo.domain.EngineerList;
import com.example.demo.domain.ProcessingType;
import com.example.demo.domain.ProgressiveStep;
import com.example.demo.domain.StoreList;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ProcessingTypeDto {
private Long serialNumber;
	
	private StoreList store;
	private DefectType defect;
	private String detailContent;
	private String receptionist;
	private String receptionistPhoneNumber;
	private EngineerList engineer;
	private LocalDateTime allocationDate;
	private LocalDateTime completionDate;
	private ProgressiveStep progressiveStep;
	private String method;
	private String inspectionStatus;
	
	@Builder
	public ProcessingTypeDto(Long serialNumber, StoreList store, DefectType defect, String detailContent,
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

	public ProcessingType toEntity() {
		return ProcessingType.builder().serialNumber(serialNumber).store(store).defect(defect).detailContent(detailContent).receptionist(receptionist).receptionistPhoneNumber(receptionistPhoneNumber)
				.engineer(engineer).allocationDate(allocationDate).completionDate(completionDate).progressiveStep(progressiveStep).method(method).inspectionStatus(inspectionStatus).build();
	}

}
