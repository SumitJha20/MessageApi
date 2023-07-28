package com.example.repository;

// UserRepository.java

// UserRepository.java

import com.example.pojo.User; // Make sure to import the correct User class
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}

