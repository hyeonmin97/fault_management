package com.example.demo.Jpatest.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.Jpatest.domain.oauth.User;

public interface UserRepository extends JpaRepository<User, Long> {
	Optional<User> findByEmail(String email);
}
