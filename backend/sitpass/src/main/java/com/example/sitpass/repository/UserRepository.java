package com.example.sitpass.repository;

import com.example.sitpass.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {
  List<User> findByRoles_Name(String roleName);
  User findByEmail(String username);
}

