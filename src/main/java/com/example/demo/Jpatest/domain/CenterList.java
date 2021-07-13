package com.example.demo.Jpatest.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.Check;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Entity
@Check(constraints = "center_state in ('영업','폐업','휴업')")
public class CenterList extends BaseTimeEntity {
	@Id
	@Column(length=10)
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long centerCode;
	
	
	@Column(length=30)
	private String centerName;
	
	@Column(length=10)
	private String centerPostalCode;
	
	
	@Column(length=60)
	private String centerAddress;
	
	@Column(length=40)
	private String centerDetailAddress;
	
	
	@Column(length=20)
	private String centerPhoneNumber;
	
	@Column(length=20)
	private String centerFaxNumber;
	
	
	@Column(length=10)
	private String centerState;

	@Builder
	public CenterList(String centerName, String centerPostalCode, String centerAddress,
			String centerDetailAddress, String centerPhoneNumber, String centerFaxNumber, String centerState) {
		this.centerName = centerName;
		this.centerPostalCode = centerPostalCode;
		this.centerAddress = centerAddress;
		this.centerDetailAddress = centerDetailAddress;
		this.centerPhoneNumber = centerPhoneNumber;
		this.centerFaxNumber = centerFaxNumber;
		this.centerState = centerState;
	}
	
	
	
}	
