package com.example.demo.Jpatest.domain.Headquarters;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
//본부 테이블
public class HeadquartersId implements Serializable{

	@Column(length=10, name="HQ_Code")
	private String hqCode;
	

	@Column(length=10, name="Area_Code")
	private String areaCode;
	
	
}
