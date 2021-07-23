package com.example.demo.dto;

import com.example.demo.domain.DefectType;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class DefectTypeDto {
	private String code;
	private String largeCategory;
	private String middleCategory;
	private String smallCategory;
	
	@Builder
	public DefectTypeDto(String code, String largeCategory, String middleCategory, String smallCategory) {
		this.code = code;
		this.largeCategory = largeCategory;
		this.middleCategory = middleCategory;
		this.smallCategory = smallCategory;
	}
	
	public DefectType toEntity() {
		return DefectType.builder()
			.code(code)
			.largeCategory(largeCategory)
			.middleCategory(middleCategory)
			.smallCategory(smallCategory)
			.build();
	}
	
}
