package com.example.sitpass.service.impl;

import com.example.sitpass.dto.UserRequest;
import com.example.sitpass.model.Role;
import com.example.sitpass.model.User;
import com.example.sitpass.repository.UserRepository;
import com.example.sitpass.service.RoleService;
import com.example.sitpass.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

  @Autowired
  private UserRepository userRepository;

  @Autowired
  private PasswordEncoder passwordEncoder;

  @Autowired
  private RoleService roleService;

  @Override
  public User findByUsername(String username) throws UsernameNotFoundException {
    return userRepository.findByEmail(username);
  }

  public User findById(Long id) throws AccessDeniedException {
    return userRepository.findById(id).orElseGet(null);
  }

  public List<User> findAll() throws AccessDeniedException {
    return userRepository.findAll();
  }

  @Override
  public User save(UserRequest userRequest) {
    User user = new User();
    user.setPassword(passwordEncoder.encode(userRequest.getPassword()));
    user.setFirstName(userRequest.getFirstName());
    user.setLastName(userRequest.getLastName());
    user.setEmail(userRequest.getEmail());
    user.setPhoneNumber(userRequest.getPhoneNumber());
    user.setAddress(userRequest.getAddress());
    user.setEnabled(true);


    List<Role> roles = roleService.findByName("USER");
    user.setRoles(roles);

    return userRepository.save(user);
  }

  @Override
  public User updateUser(String username, UserRequest userRequest) {
    User updatedUser = userRepository.findByEmail(username);
    updatedUser.setPassword(passwordEncoder.encode(userRequest.getPassword()));
    updatedUser.setFirstName(userRequest.getFirstName());
    updatedUser.setLastName(userRequest.getLastName());
    updatedUser.setPhoneNumber(userRequest.getPhoneNumber());
    updatedUser.setAddress(userRequest.getAddress());

    return this.userRepository.save(updatedUser);
  }

  @Override
  public User getCurrentUser() {
    return ((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal());
  }

}
