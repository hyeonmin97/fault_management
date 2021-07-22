package com.example.demo.Jpatest.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.Jpatest.domain.DefectType;
import com.example.demo.Jpatest.domain.PeriodicInspection;
import com.example.demo.Jpatest.domain.ProgressiveStep;
import com.example.demo.Jpatest.dto.DefectTypeDto;
import com.example.demo.Jpatest.dto.PeriodicInspectionDto;
import com.example.demo.Jpatest.dto.ProgressiveStepDto;
import com.example.demo.Jpatest.repository.DefectTypeRepository;
import com.example.demo.Jpatest.repository.ProgressiveStepRepository;

import lombok.RequiredArgsConstructor;
@RequiredArgsConstructor
@Service
public class ProgressiveStepService {
	@Autowired
	ProgressiveStepRepository repository;
	
	//전체조회
	public List<ProgressiveStepDto> getList(){
		List<ProgressiveStep> lists = repository.findAll();
		List<ProgressiveStepDto> dtoLists = new ArrayList<>();
		for(ProgressiveStep list : lists) {
			//스토어리스트 정ㅂ는 미포함
			ProgressiveStepDto dto = ProgressiveStepDto.builder()
										.number(list.getNumber())
										.content(list.getContent())
										.build();
			dtoLists.add(dto);
		}
		return dtoLists;
	}
	
	//삽입
	public void insert(ProgressiveStepDto dto) {
		if(repository.findById(dto.getNumber()).isEmpty()) {
			//값이 없을때
			repository.save(dto.toEntity());
		}
	}	
	
	//갱신
	public void update(ProgressiveStepDto dto) {
		if(repository.findById(dto.getNumber()).isPresent()) {
			//값이 있을때
			repository.save(dto.toEntity());
		}
	}
	
	//삭제
	public void delete(int number) {
		if(repository.findById(number).isPresent()){
			repository.deleteById(number);
		}
	}
}
