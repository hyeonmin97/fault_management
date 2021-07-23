package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.dto.UserInfoDto;
import com.example.demo.service.UserService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
public class LoginController {
	private final UserService userService;
	
	@GetMapping("/")
	public String index() {
		return "/index";
	}
	
	
	@GetMapping(path = "/userLogin")
	public String exLogin() {
		return "/userLogin";
	}
	
	@PostMapping(path = "/userLogin")
	public String login() {
		return "/login";
	}
	
	@GetMapping("/logout")
	public String logout() {
		//일반 로그아웃일때
		return "/logout";
	
		 
	}
	
	@GetMapping("/signup")
    public String signupForm(Model model) {
        model.addAttribute("member",new UserInfoDto());

        return "/signup";
    }
	
    @PostMapping("/signup")
    public String signUp(UserInfoDto memberDto) {
        userService.save(memberDto);

        return "redirect:/";
    }
    
    @GetMapping("/user")
    public String user() {
    	return "user";
    }
    
    @GetMapping("/amin")
    public String admin() {
    	return "admin";
    }
}
