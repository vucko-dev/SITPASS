package com.example.sitpass.service.impl;

import com.example.sitpass.dto.AccountRequestDTO;
import com.example.sitpass.dto.FacilityDTO;
import com.example.sitpass.enums.RequestStatus;
import com.example.sitpass.model.AccountRequest;
import com.example.sitpass.model.Discipline;
import com.example.sitpass.model.Facility;
import com.example.sitpass.repository.DisciplineRepository;
import com.example.sitpass.repository.FacilityRepository;
import com.example.sitpass.service.FacilityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class FacilityServiceImpl implements FacilityService {

  @Autowired
  private FacilityRepository facilityRepository;

  @Autowired
  private DisciplineRepository disciplineRepository;

  @Override
  public Facility getFacilityById(Long id) {
    return facilityRepository.findById(id).orElseGet(null);
  }

  @Override
  public Facility getFacilityByFacilityName(String facilityName) {
    return facilityRepository.findByName(facilityName);
  }

  @Override
  public List<Facility> getFacilities() {
    return facilityRepository.findAll();
  }

  @Override
  public Facility saveFacility(FacilityDTO facilityDTO) {
    Facility facility = new Facility();
    facility.setName(facilityDTO.getName());
    facility.setDescription(facilityDTO.getDescription());
    facility.setAddress(facilityDTO.getAddress());
    facility.setCity(facilityDTO.getCity());
    facility.setActive(facilityDTO.getActive());
    facility.setCreatedAt(LocalDate.now());
    facility.setTotalRating(facilityDTO.getTotalRating());
    Set<Discipline> disciplinesEntities = facilityDTO.getDisciplines().stream()
      .map(disciplineDTO -> disciplineRepository.findById(disciplineDTO.getId()).orElseThrow(() -> new RuntimeException("Discipline not found")))
      .collect(Collectors.toSet());
    facility.setDisciplines(disciplinesEntities);
    return facilityRepository.save(facility);
  }

  @Override
  public Facility updateFacility(Long id,FacilityDTO facilityDTO) {
    Facility updatedFacility = facilityRepository.findById(id).orElseGet(null);
    updatedFacility.setName(facilityDTO.getName());
    updatedFacility.setDescription(facilityDTO.getDescription());
    updatedFacility.setAddress(facilityDTO.getAddress());
    updatedFacility.setCity(facilityDTO.getCity());
    updatedFacility.setActive(facilityDTO.getActive());
    updatedFacility.setCreatedAt(LocalDate.now());
    updatedFacility.setTotalRating(facilityDTO.getTotalRating());
    Set<Discipline> disciplinesEntities = facilityDTO.getDisciplines().stream()
      .map(disciplineDTO -> disciplineRepository.findById(disciplineDTO.getId()).orElseThrow(() -> new RuntimeException("Discipline not found")))
      .collect(Collectors.toSet());
    updatedFacility.setDisciplines(disciplinesEntities);
    return this.facilityRepository.save(updatedFacility);
  }

  @Override
  public void deleteFacility(Long id) {
    if(!facilityRepository.existsById(id)) {
      throw new RuntimeException("Facility not found!");
    }
    Facility facility = facilityRepository.findById(id).orElseThrow(() -> new RuntimeException("Facility not found!"));

    facilityRepository.deleteById(id);
  }

}
