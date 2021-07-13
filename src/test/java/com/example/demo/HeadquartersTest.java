package com.example.demo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.demo.Jpatest.domain.Headquarters.Headquarters;
import com.example.demo.Jpatest.domain.Headquarters.HeadquartersId;
import com.example.demo.Jpatest.repository.HeadquartersRepository;

@SpringBootTest
public class HeadquartersTest {

	@Autowired
	private HeadquartersRepository repository;
	
	@Test
	public void headTest() {
		HeadquartersId headqutersId = new HeadquartersId();
		headqutersId.setHqCode("abc");
		headqutersId.setAreaCode("상계1");
		repository.save(Headquarters.builder()
				.headquatersId(headqutersId).build());
	}
	
}
