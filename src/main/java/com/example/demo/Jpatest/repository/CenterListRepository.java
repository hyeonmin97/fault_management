package com.example.demo.Jpatest.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.Jpatest.domain.CenterList;

public interface CenterListRepository extends JpaRepository<CenterList, Long> {
	Optional<CenterList> findByCenterCode(String code);
}
