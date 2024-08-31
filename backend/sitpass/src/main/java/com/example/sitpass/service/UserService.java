package com.example.sitpass.service;

import com.example.sitpass.dto.UserRequest;
import com.example.sitpass.model.Image;
import com.example.sitpass.model.User;

import java.util.List;

public interface UserService {
  User findById(Long id);
  User findByUsername(String username);
  List<User> findAll();
  User save(UserRequest userRequest);

  User updateUser(String username, UserRequest userRequest);

  User getCurrentUser();

  User updatePassword(String username, String newPassword);

  User updateUserImage(String username, Image image);

  User promote(String username);

  User demote(String username);
}
