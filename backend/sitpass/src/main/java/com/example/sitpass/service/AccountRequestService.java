package com.example.sitpass.service;

import com.example.sitpass.dto.AccountRequestDTO;
import com.example.sitpass.model.AccountRequest;

import java.util.List;

public interface AccountRequestService {
  AccountRequest updateAccountRequest(Long id, AccountRequestDTO accountRequestDTO);
  AccountRequest findAccountRequestById(Long id);
  AccountRequest findAccountRequestByUsername(String username);
  List<AccountRequest> findAllAccountRequests();
  AccountRequest saveAccountRequest(AccountRequestDTO accountRequestDTO);
}
