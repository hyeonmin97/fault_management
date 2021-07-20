package com.example.demo;

import java.util.List;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

import com.example.demo.Jpatest.domain.CenterList;
import com.example.demo.Jpatest.repository.querydsl.CenterListRepositorySupport;

@SpringBootTest
@ContextConfiguration(classes = DemoApplication.class)
public class QuerydslTest {
	@Autowired
	private CenterListRepositorySupport centerRepositorySupport;
	@Test
	public void query() {
		//when
		List<CenterList> list = centerRepositorySupport.findByState("영업");
		
	}
}
