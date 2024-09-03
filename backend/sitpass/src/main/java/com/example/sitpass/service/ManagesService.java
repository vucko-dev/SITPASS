package com.example.sitpass.service;

import com.example.sitpass.dto.ManagesDTO;
import com.example.sitpass.model.Manages;

import java.util.List;

public interface ManagesService {

  Boolean hasRightsToFacility(Long userId, Long facilityId);

  Manages save(ManagesDTO managesDTO);

  void delete(Long id);

  Manages findById(Long id);


  List<Manages> findByUserId(Long userId);

  Manages findByUserIdAndFacilityId(Long userId, Long facilityId);

}
