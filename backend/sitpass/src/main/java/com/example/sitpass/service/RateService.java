package com.example.sitpass.service;

import com.example.sitpass.dto.RateDTO;
import com.example.sitpass.model.Rate;

public interface RateService {

  Rate save(RateDTO rateDTO);

  void updateFacilityRating(Long id);

  void deleteFacilityRating(Long id);
}
