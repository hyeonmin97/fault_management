package com.example.demo.Jpatest.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import com.example.demo.Jpatest.domain.Headquarters.Headquarters;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Entity
public class ProgressiveStep {
	@Id
	@Column(length = 11)
	private int number;
	
	@Column(length = 60)
	private String content;

	@Builder
	public ProgressiveStep(int number, String content) {
		super();
		this.number = number;
		this.content = content;
	}
	
	
}
