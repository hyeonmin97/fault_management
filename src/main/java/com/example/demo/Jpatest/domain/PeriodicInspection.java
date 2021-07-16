package com.example.demo.Jpatest.domain;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
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
	@JoinColumn(name="storeCode", foreignKey=@ForeignKey(name="FK_PERIODICINSPECTION_STORELIST"))
	private StoreList storeList;//점포리스트의 id값

	@Column
	private LocalDateTime date;//정기점검일
	
	@Column
	private LocalDateTime nextDate;//다음 정기점검일
}
