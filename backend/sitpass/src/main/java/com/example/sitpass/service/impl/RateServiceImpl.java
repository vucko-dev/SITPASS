package com.example.sitpass.service.impl;

import com.example.sitpass.dto.RateDTO;
import com.example.sitpass.model.Facility;
import com.example.sitpass.model.Rate;
import com.example.sitpass.model.Review;
import com.example.sitpass.repository.RateRepository;
import com.example.sitpass.service.FacilityService;
import com.example.sitpass.service.RateService;
import com.example.sitpass.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RateServiceImpl implements RateService {

  @Autowired
  private RateRepository rateRepository;

  @Autowired
  private ReviewService reviewService;

  @Autowired
  private FacilityService facilityService;

  @Override
  public Rate save(RateDTO rateDTO) {
    Rate rate = new Rate();
    rate.setId(rateDTO.getId());
    rate.setEquipment(rateDTO.getEquipment());
    rate.setHygiene(rateDTO.getHygiene());
    rate.setSpace(rateDTO.getSpace());
    rate.setStuff(rateDTO.getStaff());
    return this.rateRepository.save(rate);
  }

  @Override
  public void updateFacilityRating(Long id) {
    Facility facility = facilityService.getFacilityById(id);
    if (facility != null) {
      List<Review> reviews = reviewService.getReviewsByFacilityId(facility.getId());
      double sum = (double) 0;
      for (Review review : reviews) {
        Rate rate = review.getRate();
        double avg = (double) (rate.getEquipment() + rate.getHygiene() + rate.getSpace() + rate.getStuff());
        avg = avg/4;
        sum = sum + avg;
      }
      sum = sum/reviews.size();
      facilityService.updateFacilityRating(id, sum);
    } else{
      throw new RuntimeException("Facility not found");
    }
  }
}
