package com.example.demo.Jpatest.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.Jpatest.domain.StoreList;
import com.example.demo.Jpatest.domain.Headquarters.Headquarters;
import com.example.demo.Jpatest.domain.Headquarters.HeadquartersId;
import com.example.demo.Jpatest.dto.HeadquartersDto;
import com.example.demo.Jpatest.repository.HeadquartersRepository;
import com.example.demo.Jpatest.repository.StoreListRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
@RequiredArgsConstructor
@Service
@Slf4j
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
	
//	//업데이트실패
//	public void update(HeadquartersDto dto, String column, String value) {
//		HeadquartersId i = new HeadquartersId();
//		i.setAreaCode(dto.getAreaCode());
//		i.setHqCode(dto.getHqCode());
//		Optional<Headquarters> a = headquartersRepository.findByHeadquatersId(i);
//		a.get().getHeadquatersId().setAreaCode(value);
//		
//		if(column.equals("area_code")) {
//			log.info("변경전: "+ a.get().getHeadquatersId().getAreaCode());
//			a.get().toBuilder().headquatersId(a.get().getHeadquatersId()).build();
//			log.info("변경후: "+ a.get().getHeadquatersId().getAreaCode());
//		}
////		else if(column.equals("hq_code")) {
////			log.info(dto.getAreaCode());
////			dto.toBuilder().hqCode(value).build();
////		}
//		headquartersRepository.saveAndFlush(a.get());
//
//	}
	
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
