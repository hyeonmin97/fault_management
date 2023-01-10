package com.example.demo.repository.querydsl.EngineerList;

import com.querydsl.core.types.dsl.NumberPath;
import com.querydsl.core.types.dsl.StringPath;
import com.querydsl.jpa.impl.JPAQueryFactory;

import lombok.RequiredArgsConstructor;

import static com.example.demo.domain.QEngineerList.engineerList;

import com.example.demo.dto.UpdateDto;
@RequiredArgsConstructor
public class CustomizedEngineerListRepositoryImpl implements CustomizedEngineerListRepository {
	private final JPAQueryFactory jpaQueryFactory;
	private NumberPath<Integer> selectColumn1;
	private StringPath selectColumn2;
	private NumberPath<Long> selectColumn3;

	@Override
	public void update(UpdateDto updateData) {
		// TODO Auto-generated method stub
		if(updateData.getColumnName().equals("age")) {
			selectColumn1 = engineerList.age;
			jpaQueryFactory.update(engineerList).where(engineerList.code.eq(updateData.getKeyValue())).set(selectColumn1, Integer.parseInt(updateData.getUpdateValue())).execute();
		}
		else if(updateData.getColumnName().equals("name")) {
			selectColumn2 = engineerList.name;
			jpaQueryFactory.update(engineerList).where(engineerList.code.eq(updateData.getKeyValue())).set(selectColumn2, updateData.getUpdateValue()).execute();
		}
		else if(updateData.getColumnName().equals("phone_number")) {
			selectColumn2 = engineerList.phoneNumber;
			jpaQueryFactory.update(engineerList).where(engineerList.code.eq(updateData.getKeyValue())).set(selectColumn2, updateData.getUpdateValue()).execute();
		}
		else if(updateData.getColumnName().equals("resignation_date")) {
			selectColumn2 = engineerList.resignationDate;
			jpaQueryFactory.update(engineerList).where(engineerList.code.eq(updateData.getKeyValue())).set(selectColumn2, updateData.getUpdateValue()).execute();
		}
		else if(updateData.getColumnName().equals("state")) {
			selectColumn2 = engineerList.state;
			jpaQueryFactory.update(engineerList).where(engineerList.code.eq(updateData.getKeyValue())).set(selectColumn2, updateData.getUpdateValue()).execute();
		}
		else if(updateData.getColumnName().equals("center_code")) {
			selectColumn3 = engineerList.centerList.code;
			jpaQueryFactory.update(engineerList).where(engineerList.code.eq(updateData.getKeyValue())).set(selectColumn3, Long.parseLong(updateData.getUpdateValue())).execute();
		}
		
	}
	@Override
	public void increaseAge(int value) {
		jpaQueryFactory.update(engineerList).set(engineerList.age, engineerList.age.add(value)).execute();
	}
	
	
}
