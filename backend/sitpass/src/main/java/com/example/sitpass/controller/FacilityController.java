package com.example.sitpass.controller;


import com.example.sitpass.dto.FacilityDTO;
import com.example.sitpass.dto.ImageDTO;
import com.example.sitpass.exception.ResourceConflictException;
import com.example.sitpass.model.Facility;
import com.example.sitpass.service.FacilityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/facility")
public class FacilityController {

  @Autowired
  private FacilityService facilityService;

  @GetMapping
  @PreAuthorize("hasAnyAuthority('USER', 'ADMIN', 'MANAGER')")
  public ResponseEntity<List<Facility>> getFacilities() {
      List <Facility> facilities = facilityService.getFacilities();
      return new ResponseEntity<>(facilities, HttpStatus.OK);
  }

  @GetMapping("/{id}")
  @PreAuthorize("hasAnyAuthority('USER', 'ADMIN', 'MANAGER')")
  public ResponseEntity<Facility> getFacility(@PathVariable Long id) {
      Facility facility = facilityService.getFacilityById(id);
      if (facility == null) {
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
      }
      return new ResponseEntity<>(facility, HttpStatus.OK);
  }

  @PostMapping
  @PreAuthorize("hasAuthority('ADMIN')")
  public ResponseEntity<Facility> createFacility(@RequestBody FacilityDTO facilityDTO) {
      Facility existFacility = facilityService.getFacilityByFacilityName(facilityDTO.getName());
      if (existFacility != null) {
        throw new ResourceConflictException(existFacility.getId(), "Facility already exists with name: " + facilityDTO.getName());
      }

      Facility facility = facilityService.saveFacility(facilityDTO);
      return new ResponseEntity<>(facility, HttpStatus.CREATED);
  }

  @PutMapping("/{id}")
  @PreAuthorize("hasAnyAuthority('ADMIN', 'MANAGER')")
  public ResponseEntity<Facility> updateFacility(@PathVariable Long id, @RequestBody FacilityDTO facilityDTO) {
    Facility facility = facilityService.getFacilityById(id);
    if (facility == null) {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    facilityService.updateFacility(id,facilityDTO);
    return new ResponseEntity<>(facility, HttpStatus.OK);
  }

  @DeleteMapping("/{id}")
  @PreAuthorize("hasAuthority('ADMIN')")
  public ResponseEntity<Facility> deleteFacility(@PathVariable Long id) {
    Facility facility = facilityService.getFacilityById(id);
    if (facility == null) {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    facilityService.deleteFacility(facility.getId());
    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }


  @PostMapping("/image/{id}")
  @PreAuthorize("hasAnyAuthority('ADMIN', 'MANAGER')")
  public ResponseEntity<Facility> addImage(@RequestParam("file") List<MultipartFile> files, @PathVariable Long id) throws IOException {
    if (files.isEmpty()) {
      return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
    Facility facility = facilityService.getFacilityById(id);
    if (facility == null) {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    facilityService.addImages(id,files);
    return new ResponseEntity<>(facility, HttpStatus.OK);
  }

}
