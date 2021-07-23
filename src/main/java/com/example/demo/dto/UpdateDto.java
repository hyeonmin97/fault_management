package com.example.demo.dto;


import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UpdateDto {
	private String keyValue;//검색할 키의 값
	private String columnName;//업데이트할 컬럼의 이름
	private String updateValue;//업데이트할 값
	
	@Builder
	public UpdateDto(String keyValue, String columnName, String updateValue) {
		this.keyValue = keyValue;
		this.columnName = columnName;
		this.updateValue = updateValue;
	}
	
	
	
	
}
