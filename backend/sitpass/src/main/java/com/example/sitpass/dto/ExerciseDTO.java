package com.example.sitpass.dto;

import java.time.LocalDateTime;
import java.util.List;

public class ExerciseDTO {
  private Long id;

  private Long userId;

  private LocalDateTime from;

  private LocalDateTime until;

  private Long facilityId;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Long getUserId() {
    return userId;
  }

  public void setUserId(Long userId) {
    this.userId = userId;
  }

  public LocalDateTime getFrom() {
    return from;
  }

  public void setFrom(LocalDateTime from) {
    this.from = from;
  }

  public LocalDateTime getUntil() {
    return until;
  }

  public void setUntil(LocalDateTime until) {
    this.until = until;
  }

  public Long getFacilityId() {
    return facilityId;
  }

  public void setFacilityId(Long facilityId) {
    this.facilityId = facilityId;
  }
}
