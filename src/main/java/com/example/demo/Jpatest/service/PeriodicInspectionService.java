package com.example.demo.Jpatest.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.Jpatest.domain.PeriodicInspection;
import com.example.demo.Jpatest.dto.PeriodicInspectionDto;
import com.example.demo.Jpatest.repository.PeriodicInspectionRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
@Slf4j
@RequiredArgsConstructor
@Service
public class PeriodicInspectionService {
	@Autowired
	PeriodicInspectionRepository periodicRepository;
	
	//전체조회
	public List<PeriodicInspectionDto> getList(){
		List<PeriodicInspection> lists = periodicRepository.findAll();
		List<PeriodicInspectionDto> dtoLists = new ArrayList<>();
		for(PeriodicInspection list : lists) {
			//스토어리스트 정ㅂ는 미포함
			PeriodicInspectionDto dto = PeriodicInspectionDto.builder()
											.code(list.getCode())
											.date(list.getDate())
											.nextDate(list.getNextDate())
											.build();
			dtoLists.add(dto);
		}
		return dtoLists;
	}
	
	//삽입
	public void insert(PeriodicInspectionDto dto) {
		if(periodicRepository.findById(dto.getCode()).isEmpty()) {//id가 없을때 실행
			periodicRepository.save(dto.toEntity());
		}
		else {
		}
	}
	
	//삭제
	public void delete(PeriodicInspectionDto dto) {
		if(periodicRepository.findById(dto.getCode()).isPresent()){//id가 있을때
			periodicRepository.deleteById(dto.getCode());
		}
	}
	
	//갱신
	
	public void update(PeriodicInspectionDto dto) {
		periodicRepository.save(dto.toEntity());
	}
	
	
}
