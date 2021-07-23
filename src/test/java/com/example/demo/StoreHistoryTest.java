package com.example.demo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

import com.example.demo.domain.StoreList;
import com.example.demo.service.ProcessingTypeService;

@SpringBootTest
@ContextConfiguration(classes = DemoApplication.class)
public class StoreHistoryTest {
	@Autowired
	ProcessingTypeService service;
	
	@Test
	public void test() {
		StoreList list = StoreList.builder().code("001").build();
		service.getStoreProcessHistory(list);
	}
}
