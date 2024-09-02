package com.example.sitpass.service.impl;


import com.example.sitpass.dto.WorkDayDTO;
import com.example.sitpass.model.Image;
import com.example.sitpass.model.WorkDay;
import com.example.sitpass.repository.WorkDayRepository;
import com.example.sitpass.service.WorkDayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WorkDayServiceImpl implements WorkDayService {

  @Autowired
  private WorkDayRepository workDayRepository;

  @Override
  public WorkDay findById(Long id) {
    return this.workDayRepository.findById(id).orElse(null);
  }

  @Override
  public WorkDay save(WorkDayDTO workDayDTO) {
    WorkDay workDay = new WorkDay();
    workDay.setDayOfWeek(workDayDTO.getDayOfWeek());
    workDay.setValidFrom(workDayDTO.getValidFrom());
    if(workDayDTO.getFrom().isAfter(workDayDTO.getUntil())){
      throw new RuntimeException("Greska");
    }
    workDay.setFrom(workDayDTO.getFrom());
    workDay.setUntil(workDayDTO.getUntil());
    return this.workDayRepository.save(workDay);
  }

  @Override
  public WorkDay update(WorkDayDTO workDayDTO) {
    WorkDay workDay = findById(workDayDTO.getId());
    workDay.setDayOfWeek(workDayDTO.getDayOfWeek());
    workDay.setValidFrom(workDayDTO.getValidFrom());
    if(workDayDTO.getFrom().isAfter(workDayDTO.getUntil())){
      throw new RuntimeException("Greska");
    }
    workDay.setFrom(workDayDTO.getFrom());
    workDay.setUntil(workDayDTO.getUntil());
    return this.workDayRepository.save(workDay);
  }

  @Override
  public void delete(Long id) {
    if(!this.workDayRepository.existsById(id)) {
      throw new RuntimeException("WorkDay not found!");
    }
    this.workDayRepository.deleteById(id);
  }
}
