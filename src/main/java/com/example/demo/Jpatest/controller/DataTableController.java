package com.example.demo.Jpatest.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.Jpatest.domain.EngineerList;
import com.example.demo.Jpatest.dto.CenterListDto;
import com.example.demo.Jpatest.service.CenterListService;
import com.example.demo.Jpatest.service.EngineerListService;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@AllArgsConstructor
public class DataTableController {
		@Autowired
		CenterListService centerListService;
		
		@Autowired
		EngineerListService service;
		
		@RequestMapping(value = "/dataTable")
		@ResponseBody
		public ModelAndView dataTable(HttpServletRequest request, HttpServletResponse response) throws Exception {
			ModelAndView modelAndView = new ModelAndView();
			modelAndView.setViewName("/dataTable");
			return modelAndView;
		}
		
		@PostMapping("/dataTable")
		//전체 리턴
		public @ResponseBody List<CenterListDto> list() {
			List<CenterListDto> centerList = centerListService.getList();
			return centerList;
	}

}
