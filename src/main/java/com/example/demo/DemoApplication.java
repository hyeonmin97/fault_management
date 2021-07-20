package com.example.demo;

import org.springframework.boot.SpringApplication; 
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.scheduling.annotation.EnableScheduling;

  
  @SpringBootApplication
  
  @EnableScheduling
  @EnableJpaAuditing // basetimeentity 이용해 날짜 추가 가능해짐
  public class DemoApplication {
  
  public static void main(String[] args) {
  SpringApplication.run(DemoApplication.class, args); }
  
  }
 
