package com.example.sitpass.service.impl;

import com.example.sitpass.dto.RateDTO;
import com.example.sitpass.model.Rate;
import com.example.sitpass.repository.RateRepository;
import com.example.sitpass.service.RateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RateServiceImpl implements RateService {

  @Autowired
  private RateRepository rateRepository;

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
}
