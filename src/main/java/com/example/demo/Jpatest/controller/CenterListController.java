package com.example.demo.Jpatest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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

import lombok.AllArgsConstructor;

@Controller
@AllArgsConstructor
public class CenterListController {
	@Autowired
	private CenterListService centerListService;
	
	
	//json 형식으로 리턴
	@GetMapping("/centerList")
	public @ResponseBody List<CenterListDto> list() {
		List<CenterListDto> centerList = centerListService.getList();
		return centerList;
	}
	
	@PostMapping("/centerList")
	public @ResponseBody Long write(@RequestBody CenterListDto list) {
		return centerListService.save(list).getCode();
		
	}
	//patch, put
	//patch 는 일부 수정, put은 전체 수정
	//patch는 일부만 보냈을때 그 부분이 수정되고 put은 일부만 보내면 나머지는 null으로 변함
	
	@PatchMapping("/centerList")//일부 수정
	public @ResponseBody String update(String phonNumber, String column, String value) {
		centerListService.update(phonNumber, column, value);
		return "/centerList";
	}
}
