package com.example.demo.dto;

import java.time.LocalDateTime;

import com.example.demo.domain.PeriodicInspection;
import com.example.demo.domain.StoreList;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class PeriodicInspectionDto {
	private String code;
	private StoreList storeList;
	private LocalDateTime date;
	private LocalDateTime nextDate;
	
	@Builder(toBuilder=true)
	public PeriodicInspectionDto(String code, StoreList storeList, LocalDateTime date, LocalDateTime nextDate) {
	
		this.code = code;
		this.storeList = storeList;
		this.date = date;
		this.nextDate = nextDate;
	}
	
	public PeriodicInspection toEntity() {
		return PeriodicInspection.builder()
				.code(code)
				.storeList(storeList)
				.date(date)
				.nextDate(nextDate)
				.build();
	}
	
}
