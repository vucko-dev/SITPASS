package com.example.sitpass.controller;

import com.example.sitpass.model.Discipline;
import com.example.sitpass.service.DisciplineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/disciplines")
public class DisciplineController {
  @Autowired
  private DisciplineService disciplineService;

  @GetMapping
  public ResponseEntity<List<Discipline>> getDisciplines() {
    List<Discipline> disciplines = disciplineService.getDisciplines();
    return new ResponseEntity<>(disciplines, HttpStatus.OK);  }
}
