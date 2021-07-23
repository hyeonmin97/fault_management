package com.example.demo.domain;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Entity
public class PeriodicInspection {
	
	@Id
	private String code;

	//mapsId는 @id로 지정한 컬럼에 onetoone나 manytoone관계를 매핑시키는 역할
	//매핑의 대상이 되는 속성은 onetoone나 manyToOne의 기본키와 타입이 같아야 함
	@MapsId
	@OneToOne
	@JoinColumn(name="storeCode", foreignKey=@ForeignKey(name="FK_PERIODICINSPECTION_STORELIST"))
	private StoreList storeList;//점포리스트의 id값

	@Column
	private LocalDateTime date;//정기점검일
	
	@Column
	private LocalDateTime nextDate;//다음 정기점검일
	@Builder
	public PeriodicInspection(String code, StoreList storeList, LocalDateTime date, LocalDateTime nextDate) {
		this.code = code;
		this.storeList = storeList;
		this.date = date;
		this.nextDate = nextDate;
	}
}
