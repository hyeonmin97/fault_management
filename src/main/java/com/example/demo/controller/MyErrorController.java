package com.example.demo.controller;


import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MyErrorController implements ErrorController {
 
    public String getErrorPath() {
        return "/error";
    }
 
    @RequestMapping(value = "/error")
    Object error(HttpServletRequest request, HttpServletResponse response) {
        Map<String, String> err = new HashMap<>();
        err.put("my", "custom error");
        return err;
    }
}