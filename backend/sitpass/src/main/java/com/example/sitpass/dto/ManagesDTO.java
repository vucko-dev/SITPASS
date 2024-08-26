package com.example.sitpass.dto;

import java.time.LocalDate;

public class ManagesDTO {

  private Long id;

  private LocalDate startTime;

  private LocalDate endTime;

  private Long userId;

  private Long facilityId;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public LocalDate getStartTime() {
    return startTime;
  }

  public void setStartTime(LocalDate startTime) {
    this.startTime = startTime;
  }

  public LocalDate getEndTime() {
    return endTime;
  }

  public void setEndTime(LocalDate endTime) {
    this.endTime = endTime;
  }

  public Long getUserId() {
    return userId;
  }

  public void setUserId(Long userId) {
    this.userId = userId;
  }

  public Long getFacilityId() {
    return facilityId;
  }

  public void setFacilityId(Long facilityId) {
    this.facilityId = facilityId;
  }
}
