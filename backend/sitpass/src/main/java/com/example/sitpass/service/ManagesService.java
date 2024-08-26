package com.example.sitpass.service;

import com.example.sitpass.dto.ManagesDTO;
import com.example.sitpass.model.Manages;

public interface ManagesService {

  Boolean hasRightsToFacility(Long userId, Long facilityId);

  Manages save(ManagesDTO managesDTO);

  void delete(Long id);
}
