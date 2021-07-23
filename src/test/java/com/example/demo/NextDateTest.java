package com.example.demo;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

import com.example.demo.domain.PeriodicInspection;
import com.example.demo.service.PeriodicInspectionService;

@SpringBootTest
@ContextConfiguration(classes = DemoApplication.class)
public class NextDateTest {
	@Autowired
	PeriodicInspectionService service;
	
	@Test
	public void test(){
		service.getPeriodic("2023", "08", "01");
	}
}
