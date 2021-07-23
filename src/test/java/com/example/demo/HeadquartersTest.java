package com.example.demo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.demo.domain.Headquarters.Headquarters;
import com.example.demo.domain.Headquarters.HeadquartersId;
import com.example.demo.repository.HeadquartersRepository;

@SpringBootTest
public class HeadquartersTest {

	@Autowired
	private HeadquartersRepository repository;
	
	@Test
	public void headTest() {
		HeadquartersId headqutersId = new HeadquartersId();
		HeadquartersId headqutersId2 = new HeadquartersId();
		headqutersId.setHqCode("노원");
		headqutersId.setAreaCode("상계1");
		headqutersId2.setHqCode("노원");
		headqutersId2.setAreaCode("상계2");
		repository.save(Headquarters.builder()
				.headquatersId(headqutersId).build());
		repository.save(Headquarters.builder()
				.headquatersId(headqutersId2).build());
		
	}
	
}
