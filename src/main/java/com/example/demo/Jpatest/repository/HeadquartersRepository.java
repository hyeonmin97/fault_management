package com.example.demo.Jpatest.repository;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.Jpatest.domain.Headquarters.Headquarters;
import com.example.demo.Jpatest.domain.Headquarters.HeadquartersId;
import com.example.demo.Jpatest.dto.HeadquartersDto;

@Repository
public interface HeadquartersRepository extends JpaRepository<Headquarters, HeadquartersId>{
	Optional<Headquarters> findByHeadquatersId(HeadquartersId headquatersId);
}
