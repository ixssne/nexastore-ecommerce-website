// src/main/java/com/nexastore/repository/UserRepository.java
package com.nexastore.repository;

import com.nexastore.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail(String email);
}