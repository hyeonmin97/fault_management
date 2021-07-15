package com.example.demo.Jpatest.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.Jpatest.domain.DefectType;

public interface DefectTypeRepository extends JpaRepository<DefectType, String>{

}
