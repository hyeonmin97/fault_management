package com.example.demo.Jpatest.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.Jpatest.domain.StoreList;

@Repository
public interface StoreListRepository extends JpaRepository<StoreList, String>{

}
