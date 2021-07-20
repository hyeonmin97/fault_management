package com.example.demo.Jpatest.configuration;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.querydsl.jpa.impl.JPAQueryFactory;

//프로젝트 어디에서든 jpqquery주입받을수 있게 해줌
@Configuration
public class DataBaseConfiguration {
	@PersistenceContext
	private EntityManager entityManger;
	
	@Bean
	public JPAQueryFactory jpaQueryFactory() {
		return new JPAQueryFactory(entityManger);
	}
}
