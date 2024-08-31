package com.example.sitpass.repository;

import com.example.sitpass.model.Rate;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RateRepository extends JpaRepository<Rate, Long> {
  Optional<Rate> findById(Long Id);
}
