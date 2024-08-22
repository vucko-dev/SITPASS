package com.example.sitpass.controller;

import com.example.sitpass.dto.ImageDTO;
import com.example.sitpass.dto.UserRequest;
import com.example.sitpass.model.Image;
import com.example.sitpass.model.User;
import com.example.sitpass.service.ImageService;
import com.example.sitpass.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.Principal;

@RestController
@RequestMapping(value = "/user", produces = MediaType.APPLICATION_JSON_VALUE)
public class UserController {

  @Autowired
  private UserService userService;

  @Autowired
  private ImageService imageService;

  @GetMapping
  public ResponseEntity<User> getUserInfo(Principal principal) {
    User user = userService.findByUsername(principal.getName());
    if (user != null) {
      return ResponseEntity.ok(user);
    } else {
      return ResponseEntity.notFound().build();
    }
  }


  @PutMapping
  public ResponseEntity<User> updateUserInfo(Principal principal, @RequestBody UserRequest userDto) {
    User user = userService.updateUser(principal.getName(), userDto);
    if (user != null) {
      return ResponseEntity.ok(user);
    } else {
      return ResponseEntity.notFound().build();
    }
  }

  @PutMapping("/password")
  public ResponseEntity<User> updateUserPassword(Principal principal, @RequestBody UserRequest userDto){
    User user = userService.updatePassword(principal.getName(), userDto.getPassword());
    if (user != null) {
      return ResponseEntity.ok(user);
    } else {
      return ResponseEntity.notFound().build();
    }
  }
  @PutMapping("/image")
  public ResponseEntity<User> uploadOrUpdateUserImage(
    Principal principal,
    @RequestParam("file") MultipartFile file
  ) throws IOException {

    ImageDTO imageDTO = new ImageDTO();
    imageDTO.setFile(file);
    Image savedImage = imageService.save(imageDTO);

    User user = userService.updateUserImage(principal.getName(), savedImage);

    return ResponseEntity.ok(user);
  }
}

