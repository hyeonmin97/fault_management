package com.example.demo.domain.Headquarters;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor
public class Headquarters{
	@EmbeddedId
	private HeadquartersId headquatersId;
	
	@Builder
	public Headquarters(HeadquartersId headquatersId) {
		this.headquatersId = headquatersId;
	}

	
}
