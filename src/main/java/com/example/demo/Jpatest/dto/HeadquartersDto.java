package com.example.demo.Jpatest.dto;

import com.example.demo.Jpatest.domain.Headquarters.Headquarters;
import com.example.demo.Jpatest.domain.Headquarters.HeadquartersId;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@NoArgsConstructor
public class HeadquartersDto {
	private String hqCode;
	private String areaCode;
	private HeadquartersId test;
	
	@Builder(toBuilder=true)
	public HeadquartersDto(String hqCode, String areaCode) {
		this.hqCode=hqCode;
		this.areaCode=areaCode;
	}
	
	
	public HeadquartersId makeId() {
		HeadquartersId id = new HeadquartersId(hqCode, areaCode);
		id.setAreaCode(areaCode);
		id.setHqCode(hqCode);
		return id;
	}
	
	public Headquarters toEntity() {
		return Headquarters.builder()
				.headquatersId(makeId())
				.build();
	}

	
	
	
}
