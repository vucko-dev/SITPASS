package com.example.sitpass.controller;


import com.example.sitpass.dto.ExerciseDTO;
import com.example.sitpass.model.Exercise;
import com.example.sitpass.service.ExerciseService;
import com.example.sitpass.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import com.example.sitpass.model.User;

import java.security.Principal;

@RestController
@RequestMapping("/exercise")
public class ExerciseController {

  @Autowired
  private ExerciseService exerciseService;

  @Autowired
  private UserService userService;

//  @GetMapping
//  @PreAuthorize("hasAuthority('USER')")
//  public ResponseEntity<List<ExerciseDTO>> getUserExercises(Principal principal) {
//    User user = userService.findByUsername(principal.getName());
//    List<ExerciseDTO> exercises = exerciseService.getExercisesByUserId(user.getId());
//    return new ResponseEntity<>(exercises, HttpStatus.OK);
//  }

  @GetMapping
  @PreAuthorize("hasAnyAuthority('USER', 'ADMIN', 'MANAGER')")
  public ResponseEntity<List<Exercise>> getUserExercises(Principal principal) {
    User user = userService.findByUsername(principal.getName());
    List<Exercise> exercises = exerciseService.getExercisesByUserId(user.getId());
    return new ResponseEntity<>(exercises, HttpStatus.OK);
  }

  @PostMapping
  @PreAuthorize("hasAnyAuthority('USER', 'ADMIN', 'MANAGER')")
  public ResponseEntity<ExerciseDTO> createExercise(@RequestBody ExerciseDTO exerciseDTO, Principal principal) {
    User user = userService.findByUsername(principal.getName());
    exerciseService.createExercise(exerciseDTO, user.getId());
    return new ResponseEntity<>(exerciseDTO, HttpStatus.CREATED);
  }

  @GetMapping("/{id}")
  @PreAuthorize("hasAnyAuthority('USER', 'ADMIN', 'MANAGER')")
  public ResponseEntity<Integer> getExercisesCount(Principal principal, @PathVariable Long id) {
    Long userId = userService.findByUsername(principal.getName()).getId();
    Integer num = exerciseService.getExercisesCountByFacilityId(id,userId);
    return new ResponseEntity<>(num, HttpStatus.OK);
  }
}
