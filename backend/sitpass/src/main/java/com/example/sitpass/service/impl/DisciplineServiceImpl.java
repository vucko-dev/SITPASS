package com.example.sitpass.service.impl;

import com.example.sitpass.model.Discipline;
import com.example.sitpass.repository.DisciplineRepository;
import com.example.sitpass.service.DisciplineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class DisciplineServiceImpl implements DisciplineService {

  @Autowired
  private DisciplineRepository disciplineRepository;

  @Override
  public List<Discipline> getDisciplines() {
    return disciplineRepository.findAll();
  }
}
