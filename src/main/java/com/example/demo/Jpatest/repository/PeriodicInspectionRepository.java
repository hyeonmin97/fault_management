package com.example.demo.Jpatest.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.Jpatest.domain.PeriodicInspection;
public interface PeriodicInspectionRepository extends JpaRepository<PeriodicInspection, String>  {

}
