package com.example.demo.Jpatest.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.Jpatest.domain.EngineerList;
import com.example.demo.Jpatest.dto.EngineerListDto;
import com.example.demo.Jpatest.dto.UpdateDto;
import com.example.demo.Jpatest.repository.querydsl.CenterList.CenterListRepository;
import com.example.demo.Jpatest.repository.querydsl.EngineerList.EngineerListRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class EngineerListService {
	@Autowired
	private final EngineerListRepository repository;
	
	
	public List<EngineerList> list() {
		return repository.findAll();
	}
	
	//전체 내용 삽입
	public void insert(EngineerListDto engineer) {
		if(repository.findById(engineer.getCode()).isPresent()) {//값이 있는 경우
			
		}
		else {//값이 없는 경우
			repository.save(engineer.toEntity());
		}
	}
	
	
	//일부수정
	public void update(UpdateDto updateData) {
		if(repository.findById(updateData.getKeyValue()).isPresent()) {//값이 있는 경우
			repository.update(updateData);
		}
		else {
			
		}
	}
	
	
	//삭제
	//dto로 값 왔다갔다 하래지만 아무리그래도 필드하나는 좀..
	public void delete(String code) {
		if(repository.findById(code).isPresent()) {//값이 있는경우
			repository.deleteById(code);
		}
		else {
			//삭제할 값이 없는 경우
		}
	}
	
	
	
}
