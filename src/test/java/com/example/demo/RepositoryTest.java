package com.example.demo;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.Jpatest.repository.querydsl.CenterList.CenterListRepository;

@SpringBootTest
@ContextConfiguration(classes = DemoApplication.class)
public class RepositoryTest {
	@Autowired
	CenterListRepository centerListRepository;
	@Test
	@Transactional
	//@Rollback(false) //테스트는 롤백됨, 롤백 안되게 하려면 false
	public void test() {
		centerListRepository.insert("029341111", "detail_address", "서울");
	}
}
