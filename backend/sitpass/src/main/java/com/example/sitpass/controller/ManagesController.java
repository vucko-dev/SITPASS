package com.example.sitpass.controller;


import com.example.sitpass.dto.ManagesDTO;
import com.example.sitpass.model.Facility;
import com.example.sitpass.model.Manages;
import com.example.sitpass.model.Review;
import com.example.sitpass.model.User;
import com.example.sitpass.service.FacilityService;
import com.example.sitpass.service.ManagesService;
import com.example.sitpass.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/manages")
public class ManagesController {

  @Autowired
  private ManagesService managesService;

  @Autowired
  private FacilityService facilityService;

  @Autowired
  private UserService userService;

  @PostMapping
  @PreAuthorize("hasAuthority('ADMIN')")
  public ResponseEntity<Manages> addManages(ManagesDTO managesDTO) {
    Manages manages = new Manages();
    Facility facility = facilityService.getFacilityById(managesDTO.getFacilityId());
    User user = userService.findById(managesDTO.getUserId());
    if(facility == null || user == null) {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    managesService.save(managesDTO);
    return new ResponseEntity<>(manages, HttpStatus.CREATED);
  }

  @DeleteMapping("{id}")
  @PreAuthorize("hasAuthority('ADMIN')")
  public ResponseEntity<Manages> deleteManages(@PathVariable Long id) {
    Manages manages = managesService.findById(id);
    if(manages == null) {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    managesService.delete(manages.getId());
    return new ResponseEntity<>(manages, HttpStatus.NO_CONTENT);
  }

  @GetMapping("/{id}")
  @PreAuthorize("hasAnyAuthority('USER', 'ADMIN', 'MANAGER')")
  public ResponseEntity<Boolean> hasRightToFacility(@PathVariable Long id, Principal principal) {
      User user = userService.findByUsername(principal.getName());
      Facility facility = facilityService.getFacilityById(id);
      if(facility!=null && user!=null){
        Boolean ans = managesService.hasRightsToFacility(user.getId(),id);
        return new ResponseEntity<>(ans, HttpStatus.OK);
      } else {
        return new ResponseEntity<>(false,HttpStatus.NOT_FOUND);
      }
  }
}
