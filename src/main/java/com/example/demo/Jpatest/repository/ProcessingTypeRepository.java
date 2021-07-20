package com.example.demo.Jpatest.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.Jpatest.domain.ProcessingType;

public interface ProcessingTypeRepository extends JpaRepository<ProcessingType, Long> {

}
