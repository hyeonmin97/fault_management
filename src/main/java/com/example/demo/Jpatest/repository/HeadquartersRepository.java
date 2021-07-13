package com.example.demo.Jpatest.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.Jpatest.domain.Headquarters.Headquarters;
import com.example.demo.Jpatest.domain.Headquarters.HeadquartersId;

@Repository
public interface HeadquartersRepository extends JpaRepository<Headquarters, HeadquartersId>{
	
}
