package com.example.sitpass.dto;

import com.example.sitpass.enums.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;

public class WorkDayDTO {

  private Long id;

  private LocalDate validFrom;

  private DayOfWeek dayOfWeek;

  private LocalTime from;

  private LocalTime until;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public LocalDate getValidFrom() {
    return validFrom;
  }

  public void setValidFrom(LocalDate validFrom) {
    this.validFrom = validFrom;
  }

  public DayOfWeek getDayOfWeek() {
    return dayOfWeek;
  }

  public void setDayOfWeek(DayOfWeek dayOfWeek) {
    this.dayOfWeek = dayOfWeek;
  }

  public LocalTime getFrom() {
    return from;
  }

  public void setFrom(LocalTime from) {
    this.from = from;
  }

  public LocalTime getUntil() {
    return until;
  }

  public void setUntil(LocalTime until) {
    this.until = until;
  }
}
