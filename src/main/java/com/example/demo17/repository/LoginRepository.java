package com.example.demo17.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo17.dto.User;

@Repository
public interface LoginRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
}