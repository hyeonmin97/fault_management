package com.example.demo.dto;

import javax.persistence.Column;

import com.example.demo.domain.ProgressiveStep;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ProgressiveStepDto {
	private int number;
	private String content;

	@Builder
	public ProgressiveStepDto(int number, String content) {
		this.number = number;
		this.content = content;
	}
	
	public ProgressiveStep toEntity() {
		return ProgressiveStep.builder()
				.number(number)
				.content(content)
				.build();
	}
}
