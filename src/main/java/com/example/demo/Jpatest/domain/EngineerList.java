package com.example.demo.Jpatest.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Entity
public class EngineerList {
	@Id
	@Column(length = 10)
	private String code;//사원번호
	
	@Column(length=30)
	private String name;//이름
	
	@Column(length=11)
	private int age;//나이
	
	@Column(length=20)
	private String phoneNumber;//전화번호
	
	@Column(length=10)
	private String state;//상태
	
	//외래키 들어갈 부분
	@ManyToOne
	@JoinColumn(name = "centerCode")
	private CenterList centerList; //센터코드
	
	
	@Column(length=10)
	private String startDate;//입사일
	
	@Column(length=10)
	private String resignationDate;//퇴사일
	
	
}
