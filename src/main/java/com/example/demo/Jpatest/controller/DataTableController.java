package com.example.demo.Jpatest.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.Jpatest.dto.DataTableDto;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@AllArgsConstructor
public class DataTableController {
	
	@RequestMapping(value = "/dataTable")
	@ResponseBody
	public ModelAndView dataTable(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("/dataTable");
		return modelAndView;
	}
	
	@PostMapping("/dataTable")
	//전체 리턴
	public @ResponseBody HashMap<String, Object> list(DataTableDto dataTableDto) {
		log.info(dataTableDto.toString());	
		List<HashMap<Object, String>> list = new ArrayList<HashMap<Object, String>>();
		HashMap<Object, String> hash = new HashMap<Object, String>();
		hash.put("col1", "1");
		hash.put("col2", "2");
		list.add(hash);
		
		HashMap<String, Object> json = new HashMap<String, Object>();
		json.put("data", list);
		return json;
	}
}