package com.example.demo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

import com.example.demo.Jpatest.domain.CenterList;
import com.example.demo.Jpatest.domain.StoreList;
import com.example.demo.Jpatest.domain.Headquarters.Headquarters;
import com.example.demo.Jpatest.domain.Headquarters.HeadquartersId;
import com.example.demo.Jpatest.repository.StoreListRepository;
import com.example.demo.Jpatest.repository.querydsl.CenterList.CenterListRepository;

@SpringBootTest
@ContextConfiguration(classes = DemoApplication.class)
public class StoreListTest {
	@Autowired
	StoreListRepository storeRepository;
	
	HeadquartersId headId = new HeadquartersId("노원", "상계2");
	
	@Autowired
	CenterListRepository centerRepository;
	@Test
	public void test() {
		CenterList center = centerRepository.getById((long)1);
		storeRepository.save(StoreList.builder()
				.code("001")
				.headquatersId(Headquarters.builder().headquatersId(headId).build())
				.name("아이스크림할인점")
				.postalCode("01678")
				.address("노원구 상계9동")
				.detailAddress("39길")
				.phoneNumber("029340000")
				.faxNumber("029341111")
				.dateOpen("20200112")
				.state("영업")
				.centerList(center)
				.industry("음식점")
				.build()
				);
	}
}
