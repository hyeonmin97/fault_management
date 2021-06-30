package com.example.demo.Jpatest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

	@Autowired
	private TestRepository testRepository;
	
	@GetMapping("/hi")
	public String hi() {
		return "hi";
	}
	
	@RequestMapping("/list")
	public List<Test> getList(){
		List<Test> testList = testRepository.findAll();
		return testList;
	}
}
