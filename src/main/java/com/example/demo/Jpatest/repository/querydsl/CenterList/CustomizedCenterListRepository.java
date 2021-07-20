package com.example.demo.Jpatest.repository.querydsl.CenterList;

public interface CustomizedCenterListRepository {
	//CustomizedCenterListRepositoryImpl에 작성된 메소드들을 여기에 선언해두어야 실행가능
	public void update();
	public void insert(String phoneNumber, String column, String value);
}
