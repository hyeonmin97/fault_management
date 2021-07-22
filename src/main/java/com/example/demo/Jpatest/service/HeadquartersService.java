package com.example.demo.Jpatest.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.Jpatest.domain.StoreList;
import com.example.demo.Jpatest.domain.Headquarters.Headquarters;
import com.example.demo.Jpatest.dto.HeadquartersDto;
import com.example.demo.Jpatest.repository.HeadquartersRepository;
import com.example.demo.Jpatest.repository.StoreListRepository;

import lombok.RequiredArgsConstructor;
@RequiredArgsConstructor
@Service
public class HeadquartersService {
	@Autowired
	HeadquartersRepository headquartersRepository;
	
	@Autowired
	StoreListRepository sotreListRepository;
	
	//조회
	public List<HeadquartersDto> getList(){
		List<Headquarters> headquarters = headquartersRepository.findAll();
		List<HeadquartersDto> headquartersDtoList = new ArrayList<>();
		for(Headquarters headquarter : headquarters) {
			HeadquartersDto dto = HeadquartersDto.builder()
									.hqCode(headquarter.getHeadquatersId().getHqCode())
									.areaCode(headquarter.getHeadquatersId().getAreaCode())
									.build();
			headquartersDtoList.add(dto);
		}
		return headquartersDtoList;
	}
	
	//추가
	public void insert(HeadquartersDto dto) {
		headquartersRepository.save(dto.toEntity()); 
	}
	
	//업데이트
	public void update(HeadquartersDto dto, String column, String value) {
		//전혀 모르겠는데??
		if(column.equals("area_code"))
			dto.toBuilder().areaCode(value);
		else if(column.equals("hq_code")) {
			dto.toBuilder().hqCode(value);
		}
		headquartersRepository.save(dto.toEntity());
	}
	
	//삭제
	public void delete(HeadquartersDto dto) {
		List<StoreList> list = sotreListRepository.findByHeadquatersId(dto.toEntity());
		for(StoreList data:list) {
			data.setHeadquatersId(null);
			sotreListRepository.save(data);
		}
		headquartersRepository.delete(dto.toEntity());
	}
	
}
