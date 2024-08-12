package com.example.sitpass.service.impl;

import com.example.sitpass.model.Role;
import com.example.sitpass.repository.RoleRepository;
import com.example.sitpass.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {

  @Autowired
  private RoleRepository roleRepository;

  @Override
  public Role findById(Long id) {
    Role auth = this.roleRepository.getOne(id);
    return auth;
  }

  @Override
  public List<Role> findByName(String name) {
    List<Role> roles = this.roleRepository.findByName(name);
    return roles;
  }


}
