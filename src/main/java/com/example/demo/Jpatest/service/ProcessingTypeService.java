package com.example.demo.Jpatest.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.Jpatest.domain.ProcessingType;
import com.example.demo.Jpatest.dto.ProcessingTypeDto;
import com.example.demo.Jpatest.repository.ProcessingTypeRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class ProcessingTypeService {
	@Autowired
	ProcessingTypeRepository repository;
	
	//전체조회
	public List<ProcessingTypeDto> getList() {
		List<ProcessingType> lists = repository.findAll();
		List<ProcessingTypeDto> dtoList = new ArrayList<>();
		
		for (ProcessingType list : lists ) {
			ProcessingTypeDto dto = ProcessingTypeDto.builder()
										.serialNumber(list.getSerialNumber())
										.store(list.getStore())
										.defect(list.getDefect())
										.detailContent(list.getDetailContent())
										.receptionist(list.getReceptionist())
										.receptionistPhoneNumber(list.getReceptionistPhoneNumber())
										.engineer(list.getEngineer())
										.progressiveStep(list.getProgressiveStep())
										.method(list.getMethod())
										.inspectionStatus(list.getInspectionStatus())
										.build();
			dtoList.add(dto);
		}
		return dtoList;
	}
	
	//삽입
	public void insert(ProcessingTypeDto dto) {
		if(repository.findById(dto.getSerialNumber()).isEmpty()) {
			//값이 없을때
			repository.save(dto.toEntity());
		}
	}	
	
	//갱신
	public void update(ProcessingTypeDto dto) {
		if(repository.findById(dto.getSerialNumber()).isPresent()) {
			//값이 있을때
			repository.save(dto.toEntity());
		}
	}
	
	//삭제
	public void delete(Long number) {
		if(repository.findById(number).isPresent()){
			repository.deleteById(number);
		}
	}
}
