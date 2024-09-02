package com.example.sitpass.service.impl;

import com.example.sitpass.dto.ExerciseDTO;
import com.example.sitpass.enums.DayOfWeek;
import com.example.sitpass.model.Exercise;
import com.example.sitpass.model.Facility;
import com.example.sitpass.model.User;
import com.example.sitpass.model.WorkDay;
import com.example.sitpass.repository.ExerciseRepository;
import com.example.sitpass.service.ExerciseService;
import com.example.sitpass.service.FacilityService;
import com.example.sitpass.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Set;

@Service
public class ExerciseServiceImpl implements ExerciseService {


  @Autowired
  private ExerciseRepository exerciseRepository;

  @Autowired
  private FacilityService facilityService;

  @Autowired
  private UserService userService;

  @Override
  public Exercise createExercise(ExerciseDTO exerciseDTO, Long userId) {
    Exercise exercise = new Exercise();
    User user = userService.findById(userId);
    exercise.setUser(user);
    Facility facility = facilityService.getFacilityById(exerciseDTO.getFacilityId());
    if(facility.getActive() == false){
      throw new RuntimeException("Teretana nije aktivna");
    }
    exercise.setFacility(facility);
    Set<WorkDay> workdays = facility.getWorkdays();

    LocalDateTime from = exerciseDTO.getFrom();
    LocalDateTime until = exerciseDTO.getUntil();

    if(until.isBefore(from) || until.isEqual(from)){
      throw new RuntimeException("Greska.");
    }

    DayOfWeek fromDayOfWeek = DayOfWeek.valueOf(from.getDayOfWeek().name());
    DayOfWeek untilDayOfWeek = DayOfWeek.valueOf(until.getDayOfWeek().name());

    boolean isValidTimeRange = false;

    for (WorkDay workday : workdays) {
      if (workday.getDayOfWeek() == fromDayOfWeek && workday.getDayOfWeek() == untilDayOfWeek) {
        LocalTime workdayFrom = workday.getFrom();
        LocalTime workdayUntil = workday.getUntil();

        // Check if 'from' and 'until' are within the workday's time range
        if (!from.toLocalTime().isBefore(workdayFrom) && !until.toLocalTime().isAfter(workdayUntil)) {
          isValidTimeRange = true;
          break;
        }
      }
    }

    if (!isValidTimeRange) {
      throw new IllegalArgumentException("Vreme koje ste naveli je izvan opsega radnog vremena teretane");
    }

    exercise.setFrom(from);
    exercise.setUntil(until);
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

  @Override
  public void deleteExercise(Long exerciseId) {

    this.exerciseRepository.deleteById(exerciseId);
  }
}
