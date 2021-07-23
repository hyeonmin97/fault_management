package com.example.demo.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.Builder;
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
	@JoinColumn(name = "centerCode", foreignKey=@ForeignKey(name="FK_ENGINEERLIST_CENTERLIST"))
	private CenterList centerList; //센터코드
	
	
	@Column(length=10)
	private String startDate;//입사일
	
	@Column(length=10)
	private String resignationDate;//퇴사일

	@Builder
	public EngineerList(String code, String name, int age, String phoneNumber, String state, CenterList centerList,
			String startDate, String resignationDate) {
		this.code = code;
		this.name = name;
		this.age = age;
		this.phoneNumber = phoneNumber;
		this.state = state;
		this.centerList = centerList;
		this.startDate = startDate;
		this.resignationDate = resignationDate;
	}
	
	
}
