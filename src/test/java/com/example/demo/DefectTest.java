package com.example.demo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

import com.example.demo.Jpatest.domain.DefectType;
import com.example.demo.Jpatest.dto.DefectTypeDto;
import com.example.demo.Jpatest.service.DefectTypeService;

@SpringBootTest
@ContextConfiguration(classes = DemoApplication.class)
public class DefectTest {
	@Autowired
	DefectTypeService service;
	
	@Test
	public void test() {
		DefectTypeDto a = DefectTypeDto.builder().code("99").largeCategory("HW").middleCategory("PC").smallCategory("변경한거").build();
		service.save(a);
	}
}
