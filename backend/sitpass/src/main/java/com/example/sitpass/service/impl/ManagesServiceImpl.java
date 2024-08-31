package com.example.sitpass.service.impl;

import com.example.sitpass.dto.ManagesDTO;
import com.example.sitpass.model.Manages;
import com.example.sitpass.model.User;
import com.example.sitpass.repository.ManagesRepository;
import com.example.sitpass.service.FacilityService;
import com.example.sitpass.service.ManagesService;
import com.example.sitpass.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;


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
    if(manages!=null){
      LocalDate today = LocalDate.now();
      return today.isAfter(manages.getStartTime()) && today.isBefore(manages.getEndTime());
    }
    return false;
  }

  @Override
  public Manages save(ManagesDTO managesDTO) {
    Manages manages = new Manages();
    manages.setId(managesDTO.getId());
    manages.setEndTime(managesDTO.getEndTime());
    manages.setStartTime(managesDTO.getStartTime());
    manages.setUser(userService.findById(managesDTO.getUserId()));
    manages.setFacility(facilityService.getFacilityById(managesDTO.getFacilityId()));
    User user = userService.findById(managesDTO.getUserId());
    userService.promote(user.getUsername());
    return this.managesRepository.save(manages);
  }

  @Override
  public void delete(Long id) {
    Manages manages = managesRepository.findById(id);
    List<Manages> allUserManages = managesRepository.findByUserId(manages.getUser().getId());
    if(allUserManages.size()<=0){
      userService.demote(manages.getUser().getUsername());
    }
    this.managesRepository.delete(manages);
  }

  @Override
  public Manages findById(Long id) {
    return this.managesRepository.findById(id);
  }
}
