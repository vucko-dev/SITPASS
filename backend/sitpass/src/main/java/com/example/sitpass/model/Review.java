package com.example.sitpass.model;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Table(name = "Review")
public class Review {

  @Id
  @Column(name="id")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne
  @JoinColumn(name = "user_id", nullable = false)
  private User user;

  @ManyToOne
  @JoinColumn(name = "facility_id", nullable = false)
  private Facility facility;

  @OneToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
  @JoinTable(
    name = "review_rate",
    joinColumns = @JoinColumn(name = "review_id"),
    inverseJoinColumns = @JoinColumn(name = "rate_id")
  )
  private Rate rate;

  @Column(name = "created_at")
  private LocalDateTime createdAt;

  @Column(name="exercise_count")
  private Integer exerciseCount;

  @Column(name = "hidden")
  private Boolean hidden;

  @ManyToOne
  @JoinColumn(name = "comment_id", nullable = true)
  private Comment comment;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public User getUser() {
    return user;
  }

  public void setUser(User user) {
    this.user = user;
  }

  public Facility getFacility() {
    return facility;
  }

  public void setFacility(Facility facility) {
    this.facility = facility;
  }

  public Rate getRate() {
    return rate;
  }

  public void setRate(Rate rate) {
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

  public Comment getComment() {
    return comment;
  }

  public void setComment(Comment comment) {
    this.comment = comment;
  }
}
