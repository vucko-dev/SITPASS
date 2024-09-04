package com.example.sitpass.service.impl;

import com.example.sitpass.dto.RateDTO;
import com.example.sitpass.model.Facility;
import com.example.sitpass.model.Rate;
import com.example.sitpass.model.Review;
import com.example.sitpass.repository.RateRepository;
import com.example.sitpass.repository.ReviewRepository;
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

  @Autowired
  private ReviewRepository reviewRepository;

  @Override
  public Rate save(RateDTO rateDTO) {
    Rate rate = new Rate();
    if(rateDTO.getEquipment()<1||rateDTO.getEquipment()>10){
      throw new RuntimeException("Opremljenost mora biti izmedju 1 i 10");
    }
    if(rateDTO.getHygiene()<1||rateDTO.getHygiene()>10){
      throw new RuntimeException("Higijena mora biti izmedju 1 i 10");
    }
    if(rateDTO.getSpace()<1||rateDTO.getSpace()>10){
      throw new RuntimeException("Prostor mora biti izmedju 1 i 10");
    }
    if(rateDTO.getStaff()<1||rateDTO.getStaff()>10){
      throw new RuntimeException("Osoblje mora biti izmedju 1 i 10");
    }
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
      List<Review> reviews = reviewRepository.findByFacilityId(id);
      double sum = (double) 0;
      for (Review review : reviews) {
        Rate rate = review.getRate();
        double avg = (double) (rate.getEquipment() + rate.getHygiene() + rate.getSpace() + rate.getStuff());
        avg = avg/4;
        sum = sum + avg;
      }
      if(reviews.size()==0){
        sum = 0;
      } else {
        sum = sum/reviews.size();
      }
      facilityService.updateFacilityRating(id, sum);
    } else{
      throw new RuntimeException("Facility not found");
    }
  }

  @Override
  public Double calculateTotalStaffRatingByFacilityId(Long id) {
    Facility facility = facilityService.getFacilityById(id);
    if (facility != null) {
      List<Review> reviews = reviewRepository.findByFacilityId(id);
      if(reviews.isEmpty()) return null;
      double staffTotal = (double) 0;
      for (Review review : reviews) {
        Rate rate = review.getRate();
        staffTotal += rate.getStuff();
      }
      staffTotal /= reviews.size();
      return staffTotal;
    } else {
      return null;
    }
  }

  @Override
  public Double calculateTotalEquipmentRatingByFacilityId(Long id) {
    Facility facility = facilityService.getFacilityById(id);
    if (facility != null) {
      List<Review> reviews = reviewRepository.findByFacilityId(id);
      if(reviews.isEmpty()) return null;
      double equipmentTotal = (double) 0;
      for (Review review : reviews) {
        Rate rate = review.getRate();
        equipmentTotal += rate.getEquipment();
      }
      equipmentTotal /= reviews.size();
      return equipmentTotal;
    } else {
      return null;
    }
  }

  @Override
  public Double calculateTotalSpaceRatingByFacilityId(Long id) {
    Facility facility = facilityService.getFacilityById(id);
    if (facility != null) {
      List<Review> reviews = reviewRepository.findByFacilityId(id);
      if(reviews.isEmpty()) return null;
      double spaceTotal = (double) 0;
      for (Review review : reviews) {
        Rate rate = review.getRate();
        spaceTotal += rate.getSpace();
      }
      spaceTotal /= reviews.size();
      return spaceTotal;
    } else {
      return null;
    }
  }

  @Override
  public Double calculateTotalHygieneRatingByFacilityId(Long id){
    Facility facility = facilityService.getFacilityById(id);
    if (facility != null) {
      List<Review> reviews = reviewRepository.findByFacilityId(id);
      if(reviews.isEmpty()) return null;
      double hygieneTotal = (double) 0;
      for (Review review : reviews) {
        Rate rate = review.getRate();
        hygieneTotal += rate.getHygiene();
      }
      hygieneTotal /= reviews.size();
      return hygieneTotal;
    } else {
      return null;
    }
  }

  public void deleteFacilityRating(Long id) {

    Rate rate  = rateRepository.findById(id).get();
    rateRepository.delete(rate);
  }
}
