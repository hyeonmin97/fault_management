package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.domain.DefectType;

public interface DefectTypeRepository extends JpaRepository<DefectType, String>{

}
