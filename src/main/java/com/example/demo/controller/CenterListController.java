package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.dto.CenterListDto;
import com.example.demo.dto.UpdateDto;
import com.example.demo.service.CenterListService;

import lombok.AllArgsConstructor;
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
		return centerListService.insert(list).getCode();
		
	}
	//patch, put
	//patch 는 일부 수정, put은 전체 수정
	//patch는 일부만 보냈을때 그 부분이 수정되고 put은 일부만 보내면 나머지는 null으로 변함
	
	//PatchMapping쓰고싶었지만..
	@GetMapping("/centerListPatch")
	//일부값 컬럼지정해서 수정
	public @ResponseBody String update(UpdateDto updateDto) {
		centerListService.update(updateDto);
		return "/centerList";
	}
	
	//DeleteMapping쓰고 싶었는데 안됨..
	@GetMapping("/centerListDelete")
	//컬럼삭제(기본키)
	public String delete(Long code) {
		centerListService.delete(code);
		return "/centerList";//삭제 후 이동
	}
	
}
