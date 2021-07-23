package com.example.demo;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;

import com.example.demo.dto.ProgressiveStepDto;
import com.example.demo.service.ProgressiveStepService;

@SpringBootTest
@ContextConfiguration(classes = DemoApplication.class)
public class ProgressivestepTest {
	@Autowired
	ProgressiveStepService service;
	
	@Test
	@Rollback(false)
	public void test() {
		service.getList();
		
		ProgressiveStepDto a =ProgressiveStepDto.builder().number(19).content("안녕하세요").build();
		service.insert(a);
		
		ProgressiveStepDto b =ProgressiveStepDto.builder().number(19).content("저리가세요").build();
		service.update(b);
		
		service.delete(19);
	}
	
}
