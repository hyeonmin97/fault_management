package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.domain.ProcessingType;
import com.example.demo.domain.StoreList;
import com.example.demo.service.CenterListService;
import com.example.demo.service.ProcessingTypeService;

import lombok.AllArgsConstructor;

@Controller
@AllArgsConstructor
public class ProcessingTypeController {
	@Autowired
	ProcessingTypeService service;
	
	@PostMapping("/processingType")
	//해당 대리점 과거목록 조회
	public @ResponseBody List<ProcessingType> find(@RequestBody StoreList list){
		return service.getStoreProcessHistory(list);
	}
}
