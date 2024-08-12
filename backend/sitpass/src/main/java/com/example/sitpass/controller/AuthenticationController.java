package com.example.sitpass.controller;

import com.example.sitpass.dto.JwtAuthenticationRequest;
import com.example.sitpass.dto.UserRequest;
import com.example.sitpass.dto.UserTokenState;
import com.example.sitpass.exception.ResourceConflictException;
import com.example.sitpass.model.User;
import com.example.sitpass.service.UserService;
import com.example.sitpass.utils.TokenUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping(value = "/auth", produces = MediaType.APPLICATION_JSON_VALUE)
public class AuthenticationController {

  @Autowired
  private TokenUtils tokenUtils;

  @Autowired
  private AuthenticationManager authenticationManager;

  @Autowired
  private UserService userService;

  @PostMapping("/login")
  public ResponseEntity<UserTokenState> createAuthenticationToken(
    @RequestBody JwtAuthenticationRequest authenticationRequest, HttpServletResponse response) {

    Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
      authenticationRequest.getEmail(), authenticationRequest.getPassword()));

    SecurityContextHolder.getContext().setAuthentication(authentication);

    User user = (User) authentication.getPrincipal();
    String jwt = tokenUtils.generateToken(user);
    int expiresIn = tokenUtils.getExpiredIn();

    return ResponseEntity.ok(new UserTokenState(jwt, expiresIn));
  }

  @PostMapping("/signup")
  public ResponseEntity<User> addUser(@RequestBody UserRequest userRequest, UriComponentsBuilder ucBuilder) {

    User existUser = this.userService.findByUsername(userRequest.getEmail());

    if (existUser != null) {
      throw new ResourceConflictException(userRequest.getId(), "Username already exists");
    }

    User user = this.userService.save(userRequest);

    return new ResponseEntity<>(user, HttpStatus.CREATED);
  }
}
