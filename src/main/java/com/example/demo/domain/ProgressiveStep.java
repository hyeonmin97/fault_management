package com.example.demo.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;


import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Entity
public class ProgressiveStep {
	@Id
	@Column(length = 11)
	private int number;//진행번호
	
	@Column(length = 60)
	private String content;///진행내

	@Builder
	public ProgressiveStep(int number, String content) {
		super();
		this.number = number;
		this.content = content;
	}
	
	
}
