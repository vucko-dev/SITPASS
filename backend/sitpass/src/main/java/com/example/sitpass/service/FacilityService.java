package com.example.sitpass.service;

import com.example.sitpass.dto.FacilityDTO;
import com.example.sitpass.dto.ImageDTO;
import com.example.sitpass.model.Facility;
import com.sun.org.apache.xpath.internal.operations.Mult;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface FacilityService {

  List<Facility> getFacilities();

  Facility getFacilityById(Long id);

  Facility getFacilityByFacilityName(String facilityName);

  Facility saveFacility(FacilityDTO facilityDTO);

  Facility updateFacility(Long id, FacilityDTO facilityDTO) throws RuntimeException;

  void deleteFacility(Long id);

  Facility addImagesToFacility(Long facilityId, List<ImageDTO> images) throws IOException;

  Facility updateFacilityRating(Long facilityId, Double rating);

//  List<Facility> getFacilitiesByCityName(String cityName);
  Facility addImages(Long facilityId, List<MultipartFile> images) throws IOException;
}
