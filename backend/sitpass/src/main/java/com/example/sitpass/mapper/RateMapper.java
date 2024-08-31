package com.example.sitpass.mapper;

import com.example.sitpass.model.Rate;
import com.example.sitpass.dto.RateDTO;
import org.springframework.stereotype.Component;


@Component
public class RateMapper implements MapperInterface<Rate, RateDTO> {
  public Rate toEntity(RateDTO dto) {
    Rate rate = new Rate();
    rate.setId(dto.getId());
    rate.setStuff(dto.getStaff());
    rate.setSpace(dto.getSpace());
    rate.setHygiene(dto.getHygiene());
    rate.setEquipment(dto.getEquipment());
    return rate;
  }

  public RateDTO toDto(Rate rate) {
    RateDTO dto = new RateDTO();
    dto.setId(rate.getId());
    dto.setStaff(rate.getStuff());
    dto.setSpace(rate.getSpace());
    dto.setHygiene(rate.getHygiene());
    dto.setEquipment(rate.getEquipment());
    return dto;
  }
}
