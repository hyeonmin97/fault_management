package com.example.demo;

import org.aspectj.lang.annotation.After;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

import com.example.demo.Jpatest.domain.CenterList;
import com.example.demo.Jpatest.repository.querydsl.CenterList.CenterListRepository;

@SpringBootTest
@ContextConfiguration(classes = DemoApplication.class)
public class CenterListTest {
	@Autowired
	CenterListRepository centerRepository;
	
	/*
	@AfterEach//단위 테스트 끝날때마다 실행(junit5)
	public void cleanup() {
		centerRepository.deleteAll();
	}*/
	
	@Test
	public void save() {
		centerRepository.save(CenterList.builder()
				.name("노원점")
				.postalCode("01678")
				.address("서울시 노원구 한글비석로 479")
				.detailAddress("100동")
				.phoneNumber("029430000")
				.faxNumber("029341111")
				.state("영업")
				.build());
	}
}
