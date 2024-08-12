package com.example.sitpass.service;

import com.example.sitpass.model.Role;

import java.util.List;

public interface RoleService {
  Role findById(Long id);
  List<Role> findByName(String name);
}
