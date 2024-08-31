package com.example.sitpass.repository;

import com.example.sitpass.model.Manages;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ManagesRepository extends JpaRepository<Manages, Integer> {
  List<Manages> findByUserId(Long userId);
  List<Manages> findByFacilityId(Long facilityId);
  Manages findByFacilityIdAndUserId(Long facilityId, Long userId);
  Manages findById(Long id);
}
