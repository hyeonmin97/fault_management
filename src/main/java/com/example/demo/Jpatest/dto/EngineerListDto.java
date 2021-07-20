package com.example.demo.Jpatest.dto;

import com.example.demo.Jpatest.domain.CenterList;
import com.example.demo.Jpatest.domain.EngineerList;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class EngineerListDto {
	private String code;
	private String name;
	private int age;
	private String phoneNumber;
	private String state;
	private CenterList centerList;
	private String startDate;
	private String resignationDate;
	
	@Builder
	public EngineerListDto(String code, String name, int age, String phoneNumber, String state, String startDate,
			String resignationDate) {
		this.code = code;
		this.name = name;
		this.age = age;
		this.phoneNumber = phoneNumber;
		this.state = state;
		this.startDate = startDate;
		this.resignationDate = resignationDate;
	}
	
	
	public EngineerList toEntity() {
		return EngineerList.builder()
				.code(code)
				.name(name)
				.age(age)
				.phoneNumber(phoneNumber)
				.state(state)
				.centerList(centerList)
				.startDate(startDate)
				.resignationDate(resignationDate).build();
	}

	
}
