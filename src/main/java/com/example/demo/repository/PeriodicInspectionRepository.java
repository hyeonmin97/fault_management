package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.domain.PeriodicInspection;

import java.util.List;
import java.time.LocalDate;
import java.time.LocalDateTime;
public interface PeriodicInspectionRepository extends JpaRepository<PeriodicInspection, String>  {
	List<PeriodicInspection> findByNextDate(LocalDate nextDate);
}
