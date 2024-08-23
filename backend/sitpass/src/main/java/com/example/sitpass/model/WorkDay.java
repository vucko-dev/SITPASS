package com.example.sitpass.model;

import javax.persistence.*;
import com.example.sitpass.enums.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(name="work_day")
public class WorkDay {

  @Id
  @Column(name = "id")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "valid_from")
  private LocalDate validFrom;

  @Column(name = "day_of_week")
  @Enumerated(EnumType.ORDINAL)
  private DayOfWeek dayOfWeek;

  @Column(name = "from_time")
  private LocalTime from;

  @Column(name = "until_time")
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
