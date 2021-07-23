package com.example.demo.domain.Headquarters;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
//@AllArgsConstructor
@Embeddable
@EqualsAndHashCode
//본부 테이블
public class HeadquartersId implements Serializable{

	private static final long serialVersionUID = 6227802902005703905L;

	@Column(length=10)
	private String hqCode;
	

	@Column(length=10)
	private String areaCode;
	
	@Builder(toBuilder = true)
	public HeadquartersId(String hqCode, String areaCode) {
		super();
		this.hqCode = hqCode;
		this.areaCode = areaCode;
	}
}
