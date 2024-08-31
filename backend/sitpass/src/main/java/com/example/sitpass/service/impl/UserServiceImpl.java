package com.example.sitpass.service.impl;

import com.example.sitpass.dto.UserRequest;
import com.example.sitpass.model.Image;
import com.example.sitpass.model.Role;
import com.example.sitpass.model.User;
import com.example.sitpass.repository.UserRepository;
import com.example.sitpass.service.ImageService;
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

  @Autowired
  private ImageService imageService;

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
    user.setCreatedAt(userRequest.getCreatedAt());
    user.setBirthday(userRequest.getBirthday());
    user.setCity(userRequest.getCity());
    user.setZipCode(userRequest.getZipCode());
    user.setEnabled(true);

    List<Role> roles = roleService.findByName("USER");
    user.setRoles(roles);

    return userRepository.save(user);
  }

  @Override
  public User updateUser(String username, UserRequest userRequest) {
    User updatedUser = userRepository.findByEmail(username);
//    updatedUser.setPassword(passwordEncoder.encode(userRequest.getPassword()));
    updatedUser.setFirstName(userRequest.getFirstName());
    updatedUser.setLastName(userRequest.getLastName());
    updatedUser.setPhoneNumber(userRequest.getPhoneNumber());
    updatedUser.setAddress(userRequest.getAddress());
    updatedUser.setBirthday(userRequest.getBirthday());
    updatedUser.setCity(userRequest.getCity());
    updatedUser.setZipCode(userRequest.getZipCode());
    updatedUser.setEnabled(true);
    return this.userRepository.save(updatedUser);
  }

  @Override
  public User updatePassword(String username, String password) {
    User updatedUser = userRepository.findByEmail(username);
    updatedUser.setPassword(passwordEncoder.encode(password));
    return this.userRepository.save(updatedUser);
  }

  @Override
  public User getCurrentUser() {
    return ((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal());
  }

  @Override
  public User updateUserImage(String username, Image image) {
    User updatedUser = userRepository.findByEmail(username);
    updatedUser.setImage(image);
    return this.userRepository.save(updatedUser);
  }

  @Override
  public User promote(String username){
    User user = userRepository.findByEmail(username);
    user.setRoles(roleService.findByName("MANAGER"));
    return user;
  }

  @Override
  public User demote(String username){
    User user = userRepository.findByEmail(username);
    user.setRoles(roleService.findByName("USER"));
    return user;
  }

}
