package com.example.demo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.dto.UpdateDto;
import com.example.demo.repository.querydsl.EngineerList.EngineerListRepository;

@SpringBootTest
@ContextConfiguration(classes = DemoApplication.class)
public class EngineerListTest {
	@Autowired
	EngineerListRepository repository;
	
	
	@Test
	@Transactional
	@Rollback(false)
	public void test() {
		UpdateDto dto = UpdateDto.builder()
								.keyValue("001")
								.columnName("center_code")
								.updateValue("2")
								.build();
		repository.update(dto);
	}
}
	