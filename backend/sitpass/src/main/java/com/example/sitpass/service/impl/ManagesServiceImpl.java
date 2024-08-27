package com.example.sitpass.service.impl;

import com.example.sitpass.dto.ManagesDTO;
import com.example.sitpass.model.Manages;
import com.example.sitpass.repository.ManagesRepository;
import com.example.sitpass.service.FacilityService;
import com.example.sitpass.service.ManagesService;
import com.example.sitpass.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class ManagesServiceImpl implements ManagesService {

  @Autowired
  private ManagesRepository managesRepository;

  @Autowired
  private UserService userService;

  @Autowired
  private FacilityService facilityService;

  @Override
  public Boolean hasRightsToFacility(Long userId, Long facilityId) {
    Manages manages = managesRepository.findByFacilityIdAndUserId(facilityId, userId);
    return manages != null;
  }

  @Override
  public Manages save(ManagesDTO managesDTO) {
    Manages manages = new Manages();
    manages.setId(managesDTO.getId());
    manages.setEndTime(managesDTO.getEndTime());
    manages.setStartTime(managesDTO.getStartTime());
    manages.setUser(userService.findById(managesDTO.getUserId()));
    manages.setFacility(facilityService.getFacilityById(managesDTO.getFacilityId()));
    return this.managesRepository.save(manages);
  }

  @Override
  public void delete(Long id) {
    Manages manages = managesRepository.findById(id);
    this.managesRepository.delete(manages);
  }

  @Override
  public Manages findById(Long id) {
    return this.managesRepository.findById(id);
  }
}
