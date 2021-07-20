package com.example.demo.Jpatest.repository.querydsl.CenterList;

import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.StringPath;
import com.querydsl.jpa.impl.JPAQueryFactory;

import lombok.RequiredArgsConstructor;
import static com.example.demo.Jpatest.domain.QCenterList.*;

import com.example.demo.Jpatest.domain.CenterList;

@RequiredArgsConstructor
public class CustomizedCenterListRepositoryImpl implements CustomizedCenterListRepository {
	private final JPAQueryFactory jpaQueryFactory;
	private StringPath selectColumn;


	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void insert(String phoneNumber, String column, String value) {
		
		CenterList member = jpaQueryFactory.select(Projections.fields(CenterList.class,
				centerList.code
				))
				.from(centerList)
				.where(centerList.phoneNumber.eq(phoneNumber))
				.fetchOne();
		//없는 값이 들어올 경우 nullpointerexception발생
		try {
			if(member.getCode() != null) {
				
				if(column.equals("address")) {
					selectColumn = centerList.address;
				}
				else if(column.equals("detail_address")) {
					selectColumn = centerList.detailAddress;
				}
				else if(column.equals("fax_number")) {
					selectColumn = centerList.faxNumber;
				}
				else if(column.equals("name")) {
					selectColumn = centerList.name;
				}
				else if(column.equals("phone_number")) {
					selectColumn = centerList.phoneNumber;
				}
				else if(column.equals("postal_code")) {
					selectColumn = centerList.postalCode;
				}
				else if(column.equals("state")) {
					selectColumn = centerList.state;
				}
				jpaQueryFactory.update(centerList).where(centerList.phoneNumber.eq(phoneNumber)).set(selectColumn, value).execute();
			}
		}catch(NullPointerException e){
			e.printStackTrace();
		}
			
	}
	
}
