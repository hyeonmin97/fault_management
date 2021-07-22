package com.example.demo.Jpatest.repository.querydsl.CenterList;

import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.StringPath;
import com.querydsl.jpa.impl.JPAQueryFactory;

import lombok.RequiredArgsConstructor;
import static com.example.demo.Jpatest.domain.QCenterList.*;

import com.example.demo.Jpatest.domain.CenterList;
import com.example.demo.Jpatest.dto.UpdateDto;

@RequiredArgsConstructor
public class CustomizedCenterListRepositoryImpl implements CustomizedCenterListRepository {
	private final JPAQueryFactory jpaQueryFactory;
	private StringPath selectColumn;


	@Override
	public void update(UpdateDto updateData) {
		
		CenterList member = jpaQueryFactory.select(Projections.fields(CenterList.class,
				centerList.code
				))
				.from(centerList)
				.where(centerList.phoneNumber.eq(updateData.getKeyValue()))
				.fetchOne();
		//없는 값이 들어올 경우 nullpointerexception발생
		try {
			if(member.getCode() != null) {
				
				if(updateData.getColumnName().equals("address")) {
					selectColumn = centerList.address;
				}
				else if(updateData.getColumnName().equals("detail_address")) {
					selectColumn = centerList.detailAddress;
				}
				else if(updateData.getColumnName().equals("fax_number")) {
					selectColumn = centerList.faxNumber;
				}
				else if(updateData.getColumnName().equals("name")) {
					selectColumn = centerList.name;
				}
				else if(updateData.getColumnName().equals("phone_number")) {
					selectColumn = centerList.phoneNumber;
				}
				else if(updateData.getColumnName().equals("postal_code")) {
					selectColumn = centerList.postalCode;
				}
				else if(updateData.getColumnName().equals("state")) {
					selectColumn = centerList.state;
				}
				jpaQueryFactory.update(centerList).where(centerList.phoneNumber.eq(updateData.getKeyValue())).set(selectColumn, updateData.getUpdateValue()).execute();
			}
		}catch(NullPointerException e){
			e.printStackTrace();
		}
			
	}
	
}
