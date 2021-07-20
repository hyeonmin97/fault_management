package com.example.demo.Jpatest.controller;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
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
	
	//json 형식으로 리턴
	@PostMapping("/dataTable")
	//전체 리턴
	public @ResponseBody HashMap<Object, String> list(HashMap<Object, String> param) {
		log.info(param.toString());
		HashMap<Object, String> hash = new HashMap<Object, String>();
		return hash;
	}
}