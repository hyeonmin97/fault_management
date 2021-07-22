package com.example.demo;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;

import com.example.demo.Jpatest.dto.PeriodicInspectionDto;
import com.example.demo.Jpatest.repository.PeriodicInspectionRepository;
import com.example.demo.Jpatest.service.PeriodicInspectionService;

@SpringBootTest
@ContextConfiguration(classes = DemoApplication.class)
public class PreodicTest {
	@Autowired
	private PeriodicInspectionService service;
	
	
	@Test
	@Rollback(false)
	public void test() {
		
		PeriodicInspectionDto dto = PeriodicInspectionDto.builder().code("001").date(LocalDateTime.of(2021,3,3,0,0,0)).nextDate(LocalDateTime.of(2023, 8, 1, 0, 0, 0)).build();
		//periodicRepository.save(dto.toEntity());
		service.update(dto);
	}
}
