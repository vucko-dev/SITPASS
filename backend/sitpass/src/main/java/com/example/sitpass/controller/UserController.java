package com.example.sitpass.controller;

import com.example.sitpass.dto.UserRequest;
import com.example.sitpass.model.User;
import com.example.sitpass.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.security.Principal;

@RestController
@RequestMapping(value = "/user", produces = MediaType.APPLICATION_JSON_VALUE)
public class UserController {

  @Autowired
  private UserService userService;

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
}
