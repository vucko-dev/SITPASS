package com.example.sitpass.repository;

import com.example.sitpass.model.Manages;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ManagesRepository extends JpaRepository<Manages, Integer> {
  Manages findByUserId(Long userId);
  Manages findByFacilityId(Long facilityId);
  Manages findByFacilityIdAndUserId(Long facilityId, Long userId);
  Manages findById(Long id);
}
