package com.example.demo.Jpatest.controller;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

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
	public @ResponseBody HashMap<Object, String> list(@RequestParam HashMap<String, String> param, HttpServletResponse res) {
		log.info(param.toString());
		HashMap<Object, String> hash = new HashMap<Object, String>();
		hash.put("col1", "");
		hash.put("col2", "");
		return hash;
	}
}