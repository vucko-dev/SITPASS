package com.example.sitpass.service;

import com.example.sitpass.dto.ReviewDTO;
import com.example.sitpass.model.Review;

import java.util.List;

public interface ReviewService {
  Review save(ReviewDTO reviewDTO);
  List<Review> getReviewsByUserId(Long userId);
  Review getReviewById(Long id);
  void hideReviewById(Long id);
  void showReviewById(Long id);
  List<Review> getReviewsByFacilityId(Long facilityId);

}
