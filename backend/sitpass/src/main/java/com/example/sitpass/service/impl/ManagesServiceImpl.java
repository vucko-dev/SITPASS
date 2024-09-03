package com.example.sitpass.service.impl;

import com.example.sitpass.dto.ManagesDTO;
import com.example.sitpass.model.Facility;
import com.example.sitpass.model.Manages;
import com.example.sitpass.model.User;
import com.example.sitpass.repository.FacilityRepository;
import com.example.sitpass.repository.ManagesRepository;
import com.example.sitpass.service.FacilityService;
import com.example.sitpass.service.ManagesService;
import com.example.sitpass.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;


@Service
public class ManagesServiceImpl implements ManagesService {

  @Autowired
  private ManagesRepository managesRepository;

  @Autowired
  private UserService userService;

  @Autowired
  private FacilityService facilityService;

  @Autowired
  private FacilityRepository facilityRepository;

  @Override
  public Boolean hasRightsToFacility(Long userId, Long facilityId) {
    Manages manages = managesRepository.findByFacilityIdAndUserId(facilityId, userId);
    if(manages!=null){
      LocalDate today = LocalDate.now();
      Boolean ans = today.isAfter(manages.getEndTime());
      if(ans){
        this.delete(manages.getId());
        return false;
      }
      return true;
//      else{
//        return today.isAfter(manages.getStartTime()) && today.isBefore(manages.getEndTime());
//      }
    }
    return false;
  }

  @Override
  public Manages save(ManagesDTO managesDTO) {
    Manages manages = new Manages();
//    manages.setId(managesDTO.getId());
    Manages managesCheck = managesRepository.findByFacilityIdAndUserId(managesDTO.getFacilityId(), managesDTO.getUserId());
    if(managesCheck!=null){
      throw new RuntimeException("Ovaj korisnik je vec menadzer ovog objekta.");
    }

    LocalDate from = managesDTO.getStartTime();
    LocalDate until = managesDTO.getEndTime();

    if(until.isBefore(from) || until.isEqual(from)){
      throw new RuntimeException("Greska. Proverite datume");
    }

    if(until.isBefore(LocalDate.now()) || from.isBefore(LocalDate.now())){
      throw new RuntimeException("Greska: Ne mozete staviti korisnika za menadzera na vreme pre trenutnog.");
    }

    manages.setEndTime(managesDTO.getEndTime());
    manages.setStartTime(managesDTO.getStartTime());
    manages.setUser(userService.findById(managesDTO.getUserId()));
    manages.setFacility(facilityService.getFacilityById(managesDTO.getFacilityId()));
    Facility facility = facilityService.getFacilityById(managesDTO.getFacilityId());
    facility.setActive(true);
    facilityRepository.save(facility);
    User user = userService.findById(managesDTO.getUserId());
    userService.promote(user.getUsername());
    return this.managesRepository.save(manages);
  }

  @Override
  public void delete(Long id) {
    Manages manages = managesRepository.findById(id);
    Facility facility = manages.getFacility();
    User user = manages.getUser();
    this.managesRepository.delete(manages);
    List<Manages> allUserManages = managesRepository.findByUserId(user.getId());
    if(allUserManages.size()<=0){
      userService.demote(user.getUsername());
    }
    List<Manages> facilityManages = managesRepository.findByFacilityId(facility.getId());
    if(facilityManages.isEmpty()){
      facility.setActive(false);
      facilityRepository.save(facility);
    } else {
      System.out.println(facilityManages.size());

    }
  }

  @Override
  public Manages findById(Long id) {
    return this.managesRepository.findById(id);
  }

  @Override
  public List<Manages> findByUserId(Long userId) {
    return this.managesRepository.findByUserId(userId);
  }

  @Override
  public Manages findByUserIdAndFacilityId(Long userId, Long facilityId){
    return this.managesRepository.findByFacilityIdAndUserId(facilityId, userId);
  }
}
