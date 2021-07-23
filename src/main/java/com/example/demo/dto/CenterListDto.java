package com.example.demo.dto;

import com.example.demo.domain.CenterList;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CenterListDto {
	private Long code;
	private String name;
	private String postalCode;
	private String address;
	private String detailAddress;
	private String phoneNumber;
	private String faxNumber;
	private String state;
	
	@Builder
	public CenterListDto(Long code, String name, String postalCode, String address, String detailAddress, String phoneNumber,
			String faxNumber, String state) {
		this.code = code;
		this.name = name;
		this.postalCode = postalCode;
		this.address = address;
		this.detailAddress = detailAddress;
		this.phoneNumber = phoneNumber;
		this.faxNumber = faxNumber;
		this.state = state;
	}
	
	public CenterList toEntity() {
		return CenterList.builder()
				.name(name)
				.postalCode(postalCode)
				.address(address)
				.detailAddress(detailAddress)
				.phoneNumber(phoneNumber)
				.faxNumber(faxNumber)
				.state(state)
				.build();
	}
	
}
