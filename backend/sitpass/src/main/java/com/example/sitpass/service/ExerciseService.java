package com.example.sitpass.service;

import com.example.sitpass.dto.ExerciseDTO;
import com.example.sitpass.model.Exercise;

import java.util.List;

public interface ExerciseService {
  Exercise createExercise(ExerciseDTO exerciseDTO);

//  List<ExerciseDTO> getExercisesByUserId(Long userId);
  List<Exercise> getExercisesByUserId(Long userId);

  List<Exercise> getExercisesFromFacilityByUserId(Long facilityId, Long userId);

  Integer getExercisesCountByFacilityId(Long facilityId, Long userId);
}
