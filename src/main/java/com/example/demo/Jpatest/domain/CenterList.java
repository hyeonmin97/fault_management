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
@Check(constraints = "state in ('영업','폐업','휴업')")
public class CenterList extends BaseTimeEntity {
	@Id
	@Column(length=10, name="code",insertable = false, updatable = false, nullable = false)
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long code;//센터 코드
	
	
	@Column(length=30)
	private String name;//센터명
	
	@Column(length=10)
	private String postalCode;//우편번호
	
	
	@Column(length=60)
	private String address;//주소
	
	@Column(length=40)
	private String detailAddress;//상세주소
	
	
	@Column(length=20, unique=true, nullable=false)
	private String phoneNumber;//전화번호
	
	@Column(length=20)
	private String faxNumber;//팩스번호
	
	
	@Column(length=10)
	private String state;//상태

	@Builder
	public CenterList(String name, String postalCode, String address, String detailAddress, String phoneNumber,
			String faxNumber, String state) {
		this.name = name;
		this.postalCode = postalCode;
		this.address = address;
		this.detailAddress = detailAddress;
		this.phoneNumber = phoneNumber;
		this.faxNumber = faxNumber;
		this.state = state;
	}
	
	
	
}	
