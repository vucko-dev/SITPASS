package com.example.sitpass.service.impl;

import com.example.sitpass.dto.AccountRequestDTO;
import com.example.sitpass.dto.UserRequest;
import com.example.sitpass.enums.RequestStatus;
import com.example.sitpass.model.AccountRequest;
import com.example.sitpass.repository.AccountRequestRepository;
import com.example.sitpass.service.AccountRequestService;
import com.example.sitpass.service.UserService;
import org.apache.tomcat.jni.Local;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class AccountRequestServiceImpl implements AccountRequestService {

  @Autowired
  private AccountRequestRepository accountRequestRepository;

  @Autowired
  private PasswordEncoder passwordEncoder;

  @Autowired
  private UserService userService;

  public List<AccountRequest> findAllAccountRequests() throws AccessDeniedException {
    return accountRequestRepository.findAll();
  }

  public AccountRequest findAccountRequestById(Long id) throws AccessDeniedException{
    return accountRequestRepository.findById(id).orElseGet(null);
  }

  public AccountRequest findAccountRequestByUsername(String username) throws AccessDeniedException{
    return accountRequestRepository.findByEmail(username);
  };


  public AccountRequest saveAccountRequest(AccountRequestDTO accountRequestDTO) {
    AccountRequest accountRequest = new AccountRequest();
    accountRequest.setPassword(accountRequestDTO.getPassword());
    accountRequest.setFirstName(accountRequestDTO.getFirstName());
    accountRequest.setLastName(accountRequestDTO.getLastName());
    accountRequest.setEmail(accountRequestDTO.getEmail());
    accountRequest.setPhoneNumber(accountRequestDTO.getPhoneNumber());
    accountRequest.setAddress(accountRequestDTO.getAddress());
    accountRequest.setCreatedAt(LocalDate.now());
    accountRequest.setBirthday(accountRequestDTO.getBirthday());
    accountRequest.setCity(accountRequestDTO.getCity());
    accountRequest.setZipCode(accountRequestDTO.getZipCode());
    accountRequest.setStatus(RequestStatus.PENDING);

    return accountRequestRepository.save(accountRequest);
  }


  public AccountRequest updateAccountRequest(Long id, AccountRequestDTO accountRequestDTO) {
    AccountRequest updatedAccountRequest = accountRequestRepository.findById(id).orElseGet(null);
    updatedAccountRequest.setStatus(accountRequestDTO.getStatus());

    if(accountRequestDTO.getStatus().equals(RequestStatus.ACCEPTED)){
      UserRequest userRequest = new UserRequest();
      userRequest.setEmail(updatedAccountRequest.getEmail());
      userRequest.setPassword(updatedAccountRequest.getPassword()); // Ovo prepraviti
      userRequest.setFirstName(updatedAccountRequest.getFirstName());
      userRequest.setLastName(updatedAccountRequest.getLastName());
      userRequest.setPhoneNumber(updatedAccountRequest.getPhoneNumber());
      userRequest.setAddress(updatedAccountRequest.getAddress());
      userRequest.setCreatedAt(LocalDate.now());
      userRequest.setBirthday(updatedAccountRequest.getBirthday());
      userRequest.setCity(updatedAccountRequest.getCity());
      userRequest.setZipCode(updatedAccountRequest.getZipCode());

      this.userService.save(userRequest);
    }

    return this.accountRequestRepository.save(updatedAccountRequest);
  }


}
