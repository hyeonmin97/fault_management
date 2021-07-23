package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.domain.CenterList;
import com.example.demo.dto.CenterListDto;
import com.example.demo.dto.UpdateDto;
import com.example.demo.repository.querydsl.CenterList.CenterListRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class CenterListService {
	@Autowired
	private final CenterListRepository centerListRepository;
	
	public CenterListDto findByPhoneNumber(String phoneNumber) {
		Optional<CenterList> list = centerListRepository.findByPhoneNumber(phoneNumber);
		return CenterListDto.builder()
				.code(list.get().getCode())
				.name(list.get().getName())
				.postalCode(list.get().getPostalCode())
				.address(list.get().getAddress())
				.detailAddress(list.get().getDetailAddress())
				.phoneNumber(list.get().getPhoneNumber())
				.faxNumber(list.get().getFaxNumber())
				.state(list.get().getState())
				.build();
	}
	
	public CenterList insert(CenterListDto centerListDto) {
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
	
	public void update(UpdateDto updateDto) {
		centerListRepository.update(updateDto);
		
		//업데이트 확인
		centerListRepository.findByPhoneNumber(updateDto.getKeyValue());
	}
	
	public void delete(Long id) {
		centerListRepository.deleteById(id);
	}
}
