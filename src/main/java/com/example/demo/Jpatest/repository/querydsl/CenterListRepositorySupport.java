package com.example.demo.Jpatest.repository.querydsl;


import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;

import com.example.demo.Jpatest.domain.CenterList;
import com.querydsl.jpa.impl.JPAQueryFactory;
import static com.example.demo.Jpatest.domain.QCenterList.*;

import java.util.List;
@Repository
public class CenterListRepositorySupport extends QuerydslRepositorySupport {

	private final JPAQueryFactory jpaQueryFactory;

	public CenterListRepositorySupport(JPAQueryFactory jpaQueryFactory) {
		super(CenterList.class);
		this.jpaQueryFactory = jpaQueryFactory;
	}
	
	public List<CenterList> findByState(String state) {
		return jpaQueryFactory
				.selectFrom(centerList)
				.where(centerList.state.eq(state))
				.fetch();
	}
}
