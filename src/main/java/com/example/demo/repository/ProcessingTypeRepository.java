package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.domain.ProcessingType;
import com.example.demo.domain.StoreList;

import java.util.List;

public interface ProcessingTypeRepository extends JpaRepository<ProcessingType, Long> {

	List<ProcessingType> findByStore(StoreList store);
}
