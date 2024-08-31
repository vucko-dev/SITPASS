package com.example.sitpass.repository;

import com.example.sitpass.model.Review;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReviewRepository  extends JpaRepository<Review, Long> {
  List<Review> findByUserId(Long userId);

  Review findByFacilityIdAndUserId(Long facilityId, Long userId);

  List<Review> findByFacilityId(Long facilityId);

  Review findById(long id);
}
