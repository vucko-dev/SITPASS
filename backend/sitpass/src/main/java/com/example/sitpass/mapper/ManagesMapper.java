package com.example.sitpass.mapper;

import com.example.sitpass.dto.ManagesDTO;
import com.example.sitpass.model.Manages;
import com.example.sitpass.service.FacilityService;
import com.example.sitpass.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ManagesMapper implements MapperInterface<Manages, ManagesDTO> {


  @Autowired
  private UserService userService;

  @Autowired
  private FacilityService facilityService;

  public Manages toEntity(ManagesDTO dto) {
    Manages manages = new Manages();
    manages.setId(dto.getId());
    manages.setUser(userService.findById(dto.getUserId()));
    manages.setFacility(facilityService.getFacilityById(dto.getFacilityId()));
    manages.setEndTime(dto.getEndTime());
    manages.setStartTime(dto.getStartTime());
    return manages;
  }

  public ManagesDTO toDto(Manages manages) {
    ManagesDTO dto = new ManagesDTO();
    dto.setId(manages.getId());
    dto.setUserId(manages.getUser().getId());
    dto.setFacilityId(manages.getFacility().getId());
    dto.setEndTime(manages.getEndTime());
    dto.setStartTime(manages.getStartTime());
    return dto;
  }
}
