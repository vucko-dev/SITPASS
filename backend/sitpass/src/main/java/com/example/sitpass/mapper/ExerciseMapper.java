package com.example.sitpass.mapper;

import com.example.sitpass.dto.ExerciseDTO;
import com.example.sitpass.model.Exercise;
import com.example.sitpass.service.FacilityService;
import com.example.sitpass.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ExerciseMapper implements MapperInterface<Exercise, ExerciseDTO> {


  @Autowired
  private UserService userService;

  @Autowired
  private FacilityService facilityService;

  public Exercise toEntity(ExerciseDTO exerciseDTO) {
    Exercise exercise = new Exercise();
    exercise.setId(exerciseDTO.getId());
    exercise.setUser(userService.findById(exerciseDTO.getUserId()));
    exercise.setFacility(facilityService.getFacilityById(exerciseDTO.getFacilityId()));
    exercise.setFrom(exerciseDTO.getFrom());
    exercise.setUntil(exerciseDTO.getUntil());
    return exercise;
  }

  public ExerciseDTO toDto(Exercise exercise) {
    ExerciseDTO exerciseDTO = new ExerciseDTO();
    exerciseDTO.setId(exercise.getId());
    exerciseDTO.setFrom(exercise.getFrom());
    exerciseDTO.setUntil(exercise.getUntil());
    exerciseDTO.setFacilityId(exercise.getFacility().getId());
    exerciseDTO.setUserId(exercise.getUser().getId());
    return exerciseDTO;
  }
}
