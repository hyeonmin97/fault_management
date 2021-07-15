
package com.example.demo.Jpatest.domain;


import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.example.demo.Jpatest.domain.Headquarters.Headquarters;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Getter
@NoArgsConstructor
@Entity
public class StoreList extends BaseTimeEntity{
	@Id
	@Column(length = 10)
	private String code;
	
	//본부코드,지점코드 외래키
	@ManyToOne
	@JoinColumns({
		@JoinColumn(name = "hqCode", referencedColumnName = "hqCode"),
		@JoinColumn(name = "areaCode",referencedColumnName = "areaCode")
	})
	private Headquarters headquatersId;
	
	
	@Column(length = 30)
	private String name;

	@Column(length = 10)
	private String postalCode;

	@Column(length = 60)
	private String address;

	@Column(length = 40)
	private String detailAddress;

	@Column(length = 20)
	private String phoneNumber;
	
	@Column(length = 20)
	private String faxNumber;
	
	@Column(length = 10)
	private String dateOpen;
	
	@Column(length = 10)
	private String dateClose;
	
	@Column(length = 10)
	private String state;
	
	//센터코드 외래키
	/*
	 * @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY,targetEntity =
	 * CenterList.class)//이거 없으면 java.lang.long오류남
	 * 
	 * @JoinColumn(name="centerCode")
	 */
	/*
	 * @OneToMany( targetEntity = CenterList.class , cascade = CascadeType.ALL ,
	 * fetch = FetchType.EAGER , mappedBy = "centerCode") private List<CenterList>
	 * centerCode;
	 */
	@ManyToOne(targetEntity = CenterList.class)
	@JoinColumn(name="storeCode")//컬럼의 이름 설정하는거임;
	private CenterList centerList;
	 
	@Column(length = 60)
	private String industry;


	@Builder
	public StoreList(String code, Headquarters headquatersId, String name, String postalCode, String address,
			String detailAddress, String phoneNumber, String faxNumber, String dateOpen, String dateClose, String state,
			CenterList centerList, String industry) {
		super();
		this.code = code;
		this.headquatersId = headquatersId;
		this.name = name;
		this.postalCode = postalCode;
		this.address = address;
		this.detailAddress = detailAddress;
		this.phoneNumber = phoneNumber;
		this.faxNumber = faxNumber;
		this.dateOpen = dateOpen;
		this.dateClose = dateClose;
		this.state = state;
		this.centerList = centerList;
		this.industry = industry;
	}



	


	
	
	
	
	
	
	
	

}
