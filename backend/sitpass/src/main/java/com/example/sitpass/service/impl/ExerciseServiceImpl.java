package com.example.sitpass.service.impl;

import com.example.sitpass.dto.ExerciseDTO;
import com.example.sitpass.model.Exercise;
import com.example.sitpass.model.Facility;
import com.example.sitpass.repository.ExerciseRepository;
import com.example.sitpass.service.ExerciseService;
import com.example.sitpass.service.FacilityService;
import com.example.sitpass.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExerciseServiceImpl implements ExerciseService {


  @Autowired
  private ExerciseRepository exerciseRepository;

  @Autowired
  private FacilityService facilityService;

  @Autowired
  private UserService userService;

  @Override
  public Exercise createExercise(ExerciseDTO exerciseDTO) {
    Exercise exercise = new Exercise();
    exercise.setUser(userService.findById(exerciseDTO.getUserId()));
    Facility facility = facilityService.getFacilityById(exerciseDTO.getFacilityId());
    exercise.setFacility(facility);
    exercise.setFrom(exerciseDTO.getFrom());
    exercise.setUntil(exerciseDTO.getUntil());
    return this.exerciseRepository.save(exercise);
  }

//  @Override
//  public List<ExerciseDTO> getExercisesByUserId(Long userId){
//    List <Exercise> exercises = exerciseRepository.findByUserId(userId);
//    return exercises.stream()
//      .map(exercise -> {
//        ExerciseDTO dto = new ExerciseDTO();
//
//        dto.setId(exercise.getId());
//        dto.setUserId(exercise.getUser().getId());
//        dto.setFrom(exercise.getFrom());
//        dto.setUntil(exercise.getUntil());
//        dto.setFacilityId(exercise.getFacility().getId());
//
//        return dto;
//      })
//      .collect(Collectors.toList());
//  }

  @Override
  public List<Exercise> getExercisesByUserId(Long userId){
    return this.exerciseRepository.findByUserId(userId);
  }

  @Override
  public Integer getExercisesCountByFacilityId(Long facilityId, Long userId){
    List<Exercise> exercises = this.exerciseRepository.findByFacilityIdAndUserId(facilityId, userId);
    return (int) exercises.size();
  }

  @Override
  public  List<Exercise> getExercisesFromFacilityByUserId(Long facilityId, Long userId){
    List<Exercise> exercises = this.exerciseRepository.findByFacilityIdAndUserId(facilityId, userId);
    return exercises;
  }
}
