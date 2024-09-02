package com.example.sitpass.controller;


import com.example.sitpass.model.Facility;
import com.example.sitpass.service.FacilityService;
import com.example.sitpass.service.RateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/rate")
public class RateController {
  @Autowired
  private RateService rateService;

  @Autowired
  private FacilityService facilityService;

  @GetMapping("/staff/{id}")
  @PreAuthorize("hasAnyAuthority('USER', 'ADMIN', 'MANAGER')")
  public ResponseEntity<Double>getTotalStaffRatingByFacilityId(@PathVariable("id") Long id){
    Facility facility = facilityService.getFacilityById(id);
    if(facility == null){
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    Double ans = rateService.calculateTotalStaffRatingByFacilityId(id);
    return new ResponseEntity<>(ans, HttpStatus.OK);
  }

  @GetMapping("/hygiene/{id}")
  @PreAuthorize("hasAnyAuthority('USER', 'ADMIN', 'MANAGER')")
  public ResponseEntity<Double>getTotalHygieneRatingByFacilityId(@PathVariable("id") Long id){
    Facility facility = facilityService.getFacilityById(id);
    if(facility == null){
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    Double ans = rateService.calculateTotalHygieneRatingByFacilityId(id);
    return new ResponseEntity<>(ans, HttpStatus.OK);
  }

  @GetMapping("/space/{id}")
  @PreAuthorize("hasAnyAuthority('USER', 'ADMIN', 'MANAGER')")
  public ResponseEntity<Double>getTotalSpaceRatingByFacilityId(@PathVariable("id") Long id){
    Facility facility = facilityService.getFacilityById(id);
    if(facility == null){
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    Double ans = rateService.calculateTotalSpaceRatingByFacilityId(id);
    return new ResponseEntity<>(ans, HttpStatus.OK);
  }

  @GetMapping("/equipment/{id}")
  @PreAuthorize("hasAnyAuthority('USER', 'ADMIN', 'MANAGER')")
  public ResponseEntity<Double>getTotalEquipmentRatingByFacilityId(@PathVariable("id") Long id){
    Facility facility = facilityService.getFacilityById(id);
    if(facility == null){
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    Double ans = rateService.calculateTotalEquipmentRatingByFacilityId(id);
    return new ResponseEntity<>(ans, HttpStatus.OK);
  }
}
