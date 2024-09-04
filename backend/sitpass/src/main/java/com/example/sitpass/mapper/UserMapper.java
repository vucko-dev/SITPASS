package com.example.sitpass.mapper;

import com.example.sitpass.dto.UserRequest;
import com.example.sitpass.model.Image;
import com.example.sitpass.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.example.sitpass.service.ImageService;


import java.io.IOException;
import java.util.Base64;
import java.util.HashSet;
import java.util.stream.Collectors;


@Component
public class UserMapper implements MapperInterface<User, UserRequest> {

  @Autowired
  private ImageService imageService;


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
    Image img = imageService.findById(dto.getImage().getId());
    user.setImage(img);
    user.setEnabled(true);
    user.setCreatedAt(dto.getCreatedAt());
    user.setAddress(dto.getAddress());
    user.setPhoneNumber(dto.getPhoneNumber());

    return user;
  }

  @Override
  public UserRequest toDto(User entity) {
    if (entity == null) {
      return null;
    }

    UserRequest userRequest = new UserRequest();

    userRequest.setId(entity.getId());
    userRequest.setFirstName(entity.getFirstName());
    userRequest.setLastName(entity.getLastName());
    userRequest.setEmail(entity.getEmail());
    userRequest.setPassword(null);
    userRequest.setCity(entity.getCity());
    userRequest.setZipCode(entity.getZipCode());
    userRequest.setBirthday(entity.getBirthday());
    userRequest.setAddress(entity.getAddress());
    userRequest.setCreatedAt(entity.getCreatedAt());
    userRequest.setPhoneNumber(entity.getPhoneNumber());
    userRequest.setImage(null);

    return userRequest;
  }
}

