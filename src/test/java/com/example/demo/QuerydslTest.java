package com.example.demo;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import javax.persistence.EntityManager;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

import com.example.demo.Jpatest.domain.CenterList;
import com.example.demo.Jpatest.domain.QCenterList;
import com.example.demo.Jpatest.repository.querydsl.CenterListRepositorySupport;
import com.querydsl.jpa.impl.JPAQueryFactory;

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
