package com.example.demo.Jpatest.service;

import java.util.ArrayList;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.Jpatest.domain.CenterList;
import com.example.demo.Jpatest.dto.CenterListDto;
import com.example.demo.Jpatest.repository.querydsl.CenterList.CenterListRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class CenterListService {
	@Autowired
	private final CenterListRepository centerListRepository;
	
	public CenterList save(CenterListDto centerListDto) {
		return centerListRepository.save(centerListDto.toEntity());
	}
	
	public List<CenterListDto> getList(){
		List<CenterList> centers = centerListRepository.findAll();
		List<CenterListDto> centerDtoList = new ArrayList<>();
		
		for (CenterList center : centers ) {
			CenterListDto dto = CenterListDto.builder()
								.code(center.getCode())
								.name(center.getName())
								.postalCode(center.getPostalCode())
								.address(center.getAddress())
								.detailAddress(center.getDetailAddress())
								.phoneNumber(center.getPhoneNumber())
								.faxNumber(center.getFaxNumber())
								.state(center.getState())
								.build();
			centerDtoList.add(dto);
		}
		return centerDtoList;
	}
	
	public void update(String phoneNumber, String column, String value) {
		centerListRepository.insert(phoneNumber, column, value);
		
		//업데이트 확인
		centerListRepository.findByPhoneNumber(phoneNumber);
	}
	
	public void delete(Long id) {
		centerListRepository.deleteById(id);
	}
}
