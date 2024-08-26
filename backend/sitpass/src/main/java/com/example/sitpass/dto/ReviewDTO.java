package com.example.sitpass.dto;

import java.time.LocalDateTime;

public class ReviewDTO {

  private Long id;

  private Long userId;

  private Long facilityId;

  private RateDTO rate;

  private LocalDateTime createdAt;

  private Integer exerciseCount;

  private Boolean hidden;

  private CommentDTO commentDTO;

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

  public Long getFacilityId() {
    return facilityId;
  }

  public void setFacilityId(Long facilityId) {
    this.facilityId = facilityId;
  }

  public RateDTO getRate() {
    return rate;
  }

  public void setRate(RateDTO rate) {
    this.rate = rate;
  }

  public LocalDateTime getCreatedAt() {
    return createdAt;
  }

  public void setCreatedAt(LocalDateTime createdAt) {
    this.createdAt = createdAt;
  }

  public Integer getExerciseCount() {
    return exerciseCount;
  }

  public void setExerciseCount(Integer exerciseCount) {
    this.exerciseCount = exerciseCount;
  }

  public Boolean getHidden() {
    return hidden;
  }

  public void setHidden(Boolean hidden) {
    this.hidden = hidden;
  }

  public CommentDTO getCommentDTO() {
    return commentDTO;
  }

  public void setCommentDTO(CommentDTO commentDTO) {
    this.commentDTO = commentDTO;
  }
}
