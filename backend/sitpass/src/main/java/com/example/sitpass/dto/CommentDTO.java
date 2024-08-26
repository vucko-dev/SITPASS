package com.example.sitpass.dto;


import java.time.LocalDateTime;
import java.util.Set;

public class CommentDTO {

  private Long id;

  private String text;

  private Long userId;

  private Long reviewId;

  private LocalDateTime createdAt;

  private Long parentCommentId;

  private Set<CommentDTO> replies;


  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getText() {
    return text;
  }

  public void setText(String text) {
    this.text = text;
  }

  public Long getUserId() {
    return userId;
  }

  public void setUserId(Long userId) {
    this.userId = userId;
  }

  public Long getReviewId() {
    return reviewId;
  }

  public void setReviewId(Long reviewId) {
    this.reviewId = reviewId;
  }

  public LocalDateTime getCreatedAt() {
    return createdAt;
  }

  public void setCreatedAt(LocalDateTime createdAt) {
    this.createdAt = createdAt;
  }

  public Long getParentCommentId() {
    return parentCommentId;
  }

  public void setParentCommentId(Long parentCommentId) {
    this.parentCommentId = parentCommentId;
  }

  public Set<CommentDTO> getReplies() {
    return replies;
  }

  public void setReplies(Set<CommentDTO> replies) {
    this.replies = replies;
  }
}
