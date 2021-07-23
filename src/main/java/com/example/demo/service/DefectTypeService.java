package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.domain.DefectType;
import com.example.demo.dto.DefectTypeDto;
import com.example.demo.repository.DefectTypeRepository;
import com.example.demo.repository.querydsl.EngineerList.EngineerListRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class DefectTypeService {
	@Autowired
	DefectTypeRepository repository;
	
	//전체조회
	public List<DefectTypeDto> getList(){
		List<DefectType> lists = repository.findAll();
		List<DefectTypeDto> dtoList = new ArrayList<>();
		for(DefectType list : lists) {
			DefectTypeDto dto = DefectTypeDto.builder()
									.code(list.getCode())
									.largeCategory(list.getLargeCategory())
									.middleCategory(list.getMiddleCategory())
									.smallCategory(list.getSmallCategory())
									.build();
			dtoList.add(dto);
		}
		return dtoList;
	}
	
	//삽입
	public void insert(DefectTypeDto dto) {
		if(repository.findById(dto.getCode()).isEmpty()) {
			//값이 없을때
			repository.save(dto.toEntity());
		}
	}	
	
	//갱신
	public void update(DefectTypeDto dto) {
		if(repository.findById(dto.getCode()).isPresent()) {
			//값이 있을때
			repository.save(dto.toEntity());
		}
	}
	
	//삭제
	public void delete(String code) {
		if(repository.findById(code).isPresent()){
			repository.deleteById(code);
		}
	}
}
