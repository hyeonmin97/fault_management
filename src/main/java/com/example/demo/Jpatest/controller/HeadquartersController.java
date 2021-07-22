package com.example.demo.Jpatest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.Jpatest.dto.HeadquartersDto;
import com.example.demo.Jpatest.service.HeadquartersService;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
@Slf4j
@Controller
@AllArgsConstructor
public class HeadquartersController {
	@Autowired
	private HeadquartersService headquartersService;
	
	@GetMapping("/headquarters")
	public @ResponseBody List<HeadquartersDto> list(){
		List<HeadquartersDto> list = headquartersService.getList();
		return list;
	}
	
	@PostMapping("/headquarters")
	//로우 추가
	public @ResponseBody String insert(@RequestBody HeadquartersDto dto) {
		log.info(dto.getAreaCode());
		log.info(dto.getHqCode());
		headquartersService.insert(dto);
		return "/headquarters";//전체 리스트 확인
	}
	
//	@PostMapping("/headquartersUpdate")
//	//업데이트
//	public @ResponseBody void update(@RequestBody HeadquartersDto dto, String column, String value){
//		headquartersService.update(dto, column, value);
//	}
	
}
