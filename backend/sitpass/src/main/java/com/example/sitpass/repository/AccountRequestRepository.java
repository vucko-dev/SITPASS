package com.example.sitpass.repository;

import com.example.sitpass.model.AccountRequest;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRequestRepository extends JpaRepository<AccountRequest, Long> {
  AccountRequest findByEmail(String username);
}
