package com.example.demo.Jpatest.controller;

import java.util.List;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.Jpatest.domain.CenterList;
import com.example.demo.Jpatest.dto.CenterListDto;
import com.example.demo.Jpatest.service.CenterListService;
import com.fasterxml.jackson.core.JsonToken;
import com.mysql.cj.xdevapi.JsonNumber;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
@Slf4j
@Controller
@AllArgsConstructor
public class CenterListController {
	@Autowired
	private CenterListService centerListService;
	
	
	//json 형식으로 리턴
	@GetMapping("/centerList")
	//전체 리턴
	public @ResponseBody List<CenterListDto> list() {
		List<CenterListDto> centerList = centerListService.getList();
		return centerList;
	}
	
	@PostMapping("/centerList")
	//전체값 입력해서 추가
	public @ResponseBody Long write(@RequestBody CenterListDto list) {
		return centerListService.save(list).getCode();
		
	}
	//patch, put
	//patch 는 일부 수정, put은 전체 수정
	//patch는 일부만 보냈을때 그 부분이 수정되고 put은 일부만 보내면 나머지는 null으로 변함
	
	//PatchMapping쓰고싶었지만..
	@GetMapping("/centerListPatch")
	//일부값 컬럼지정해서 수정
	public @ResponseBody String update(String phonNumber, String column, String value) {
		centerListService.update(phonNumber, column, value);
		return "/centerList";
	}
	
	//DeleteMapping쓰고 싶었는데 안됨..
	@GetMapping("/centerListDelete")
	//컬럼삭제(기본키)
	public String delete(Long code) {
		centerListService.delete(code);
		return "/centerList";
	}
	
}
