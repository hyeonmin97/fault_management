package com.example.demo.Jpatest.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Entity
public class DefectType {//장애유형 테이블
	@Id
	@Column(length = 10)
	private String code;
	
	@Column(length = 45)
	private String largeCategory;
	
	@Column(length = 45)
	private String middleCategory;
	
	@Column(length = 45)
	private String smallCategory;

	@Builder
	public DefectType(String code, String largeCategory, String middleCategory, String smallCategory) {
		this.code = code;
		this.largeCategory = largeCategory;
		this.middleCategory = middleCategory;
		this.smallCategory = smallCategory;
	}
	
	
	
}
