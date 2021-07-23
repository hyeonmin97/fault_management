package com.example.demo.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.domain.StoreList;
import com.example.demo.domain.Headquarters.Headquarters;
import com.example.demo.domain.Headquarters.HeadquartersId;

@Repository
public interface StoreListRepository extends JpaRepository<StoreList, String>{

	List<StoreList> findByHeadquatersId(Headquarters headquatersId);
}
