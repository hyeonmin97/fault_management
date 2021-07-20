package com.example.demo.Jpatest.repository.querydsl.EngineerList;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.Jpatest.domain.EngineerList;

@Repository
public interface EngineerListRepository extends JpaRepository<EngineerList, String>, CustomizedEngineerListRepository{
	
	
}
