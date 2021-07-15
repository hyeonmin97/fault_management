package com.example.demo.Jpatest.domain;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Entity
public class PeriodicInspection {
	
	@Id
	private String code;

	@MapsId
	@OneToOne
	@JoinColumn(name="storeCode")
	private StoreList storeList;

	@Column
	private LocalDateTime date;
	
	@Column
	private LocalDateTime nextDate;
}
