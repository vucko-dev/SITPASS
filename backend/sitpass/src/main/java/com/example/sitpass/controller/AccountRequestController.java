package com.example.sitpass.controller;

import com.example.sitpass.dto.AccountRequestDTO;
import com.example.sitpass.exception.ResourceConflictException;
import com.example.sitpass.model.AccountRequest;
import com.example.sitpass.model.User;
import com.example.sitpass.service.AccountRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/requests", produces = MediaType.APPLICATION_JSON_VALUE)
public class AccountRequestController {

  @Autowired
  private AccountRequestService accountRequestService;

  @GetMapping
  @PreAuthorize("hasAuthority('ADMIN')")
  public ResponseEntity<List<AccountRequest>> getAccountRequests() {
    List <AccountRequest> accountRequests = accountRequestService.findAllAccountRequests();
    return new ResponseEntity<>(accountRequests, HttpStatus.OK);
  }

  @GetMapping("/{id}")
  @PreAuthorize("hasAuthority('ADMIN')")
  public ResponseEntity<AccountRequest> getAccountRequest(@PathVariable Long id) {
    AccountRequest accountRequest = accountRequestService.findAccountRequestById(id);
    return new ResponseEntity<>(accountRequest, HttpStatus.OK);
  }

  @PostMapping
  public ResponseEntity<AccountRequest> createAccountRequest(@RequestBody AccountRequestDTO accountRequestDTO) {
    AccountRequest existRequest = this.accountRequestService.findAccountRequestByUsername(accountRequestDTO.getEmail());
    if (accountRequestDTO.getEmail().isEmpty() || accountRequestDTO.getPassword().isEmpty()) {
      return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
    if (existRequest != null) {
      throw new ResourceConflictException(existRequest.getId(), "Email already exists");
    }
    AccountRequest savedRequest = accountRequestService.saveAccountRequest(accountRequestDTO);
    return new ResponseEntity<>(savedRequest, HttpStatus.CREATED);
  }

  @PutMapping("/{id}")
  @PreAuthorize("hasAuthority('ADMIN')")
  public ResponseEntity<AccountRequest> updateAccountRequest(@PathVariable Long id, @RequestBody AccountRequestDTO accountRequestDTO) {
    AccountRequest savedRequest = accountRequestService.updateAccountRequest(id, accountRequestDTO);
    return new ResponseEntity<>(savedRequest, HttpStatus.OK);
  }





}
