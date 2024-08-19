package com.example.sitpass.service;

import com.example.sitpass.dto.FacilityDTO;
import com.example.sitpass.model.Facility;

import java.util.List;

public interface FacilityService {

  List<Facility> getFacilities();

  Facility getFacilityById(Long id);

  Facility getFacilityByFacilityName(String facilityName);

  Facility saveFacility(FacilityDTO facilityDTO);

  Facility updateFacility(Long id, FacilityDTO facilityDTO);

  void deleteFacility(Long id);
}
