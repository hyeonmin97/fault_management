package com.example.demo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;

import com.example.demo.Jpatest.domain.Headquarters.HeadquartersId;
import com.example.demo.Jpatest.dto.HeadquartersDto;
import com.example.demo.Jpatest.dto.HeadquartersDto.HeadquartersDtoBuilder;
import com.example.demo.Jpatest.service.HeadquartersService;

import lombok.extern.slf4j.Slf4j;

@SpringBootTest
@Slf4j
@ContextConfiguration(classes = DemoApplication.class)
public class headquartersDtoDelete {
	@Autowired
	HeadquartersService service;
	
//	@Test
//	@Rollback(false)
//	public void test() {
//		HeadquartersId i = new HeadquartersId("도봉","창동1");
//		HeadquartersDto dto = HeadquartersDto.builder().areaCode("창동2").hqCode("도봉").build();
//							
//		log.info(dto.getAreaCode());
//		service.delete(dto);
//	}
	
	@Test
	public void test() {
		HeadquartersDto dto = HeadquartersDto.builder().areaCode("논현1").hqCode("강남").build();
		
		service.update(dto, "area_code", "변경");
	}
}
