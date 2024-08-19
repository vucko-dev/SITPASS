package com.example.sitpass.mapper;

import com.example.sitpass.dto.UserRequest;
import com.example.sitpass.model.User;


import java.io.IOException;
import java.util.Base64;
import java.util.HashSet;
import java.util.stream.Collectors;

public class UserMapper implements MapperInterface<User, UserRequest> {

  public UserMapper() {

  }
  @Override
  public User toEntity(UserRequest dto){
    if (dto == null) {
      return null;
    }

    User user = new User();

    user.setId(dto.getId());
    user.setFirstName(dto.getFirstName());
    user.setLastName(dto.getLastName());
    user.setEmail(dto.getEmail());
    user.setPassword(dto.getPassword());
    user.setCity(dto.getCity());
    user.setZipCode(dto.getZipCode());
    user.setBirthday(dto.getBirthday());
//    if(dto.getImageFromFrontend()!=null){
//      try {
//        user.setImage(dto.getImageFromFrontend().getBytes());
//      } catch (IOException e) {
//        throw new RuntimeException("Greška prilikom čitanja slike", e);
//      }
//    }

    return user;
  }

  // Pretvara entitet u DTO
  @Override
  public UserRequest toDto(User entity) {
    if (entity == null) {
      return null;
    }

    UserRequest userRequest = new UserRequest();

    userRequest.setId(entity.getId());
    userRequest.setFirstName(userRequest.getFirstName());
    userRequest.setLastName(userRequest.getLastName());
    userRequest.setEmail(userRequest.getEmail());
    userRequest.setPassword(userRequest.getPassword());
    userRequest.setCity(userRequest.getCity());
    userRequest.setZipCode(userRequest.getZipCode());
    userRequest.setBirthday(userRequest.getBirthday());
//    if(entity.getImage()!=null) {
//      userRequest.setImageFromBackend(Base64.getEncoder().encodeToString(entity.getImage()));
//    }
    return userRequest;
  }
}

