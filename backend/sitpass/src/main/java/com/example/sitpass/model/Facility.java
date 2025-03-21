package com.example.sitpass.model;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Set;

@Entity
@Table(name="Facility")
public class Facility {

  @Id
  @Column(name = "id")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "name")
  private String name;

  @Column(name = "description")
  private String description;

  @Column(name = "created_at")
  private LocalDate createdAt;

  @Column(name = "address")
  private String address;

  @Column(name = "city")
  private String city;

  @Column(name = "total_rating")
  private Double totalRating;

  @Column(name = "active")
  private Boolean active = true;

  @ManyToMany(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
  @JoinTable(
    name = "facility_disciplines",
    joinColumns = @JoinColumn(name = "facility_id"),
    inverseJoinColumns = @JoinColumn(name = "discipline_id")
  )
  private Set<Discipline> disciplines;

  @ManyToMany(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
  @JoinTable(
    name = "facility_workdays",
    joinColumns = @JoinColumn(name = "facility_id"),
    inverseJoinColumns = @JoinColumn(name = "workday_id")
  )
  private Set<WorkDay> workdays;


  @ManyToMany(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
  @JoinTable(
    name = "facility_images",
    joinColumns = @JoinColumn(name = "facility_id"),
    inverseJoinColumns = @JoinColumn(name = "image_id")
  )
  private Set<Image> images;


  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public LocalDate getCreatedAt() {
    return createdAt;
  }

  public void setCreatedAt(LocalDate createdAt) {
    this.createdAt = createdAt;
  }

  public String getAddress() {
    return address;
  }

  public void setAddress(String address) {
    this.address = address;
  }

  public String getCity() {
    return city;
  }

  public void setCity(String city) {
    this.city = city;
  }

  public Double getTotalRating() {
    return totalRating;
  }

  public void setTotalRating(Double totalRating) {
    this.totalRating = totalRating;
  }

  public Boolean getActive() {
    return active;
  }

  public void setActive(Boolean active) {
    this.active = active;
  }

  public Set<Discipline> getDisciplines() {
    return disciplines;
  }

  public void setDisciplines(Set<Discipline> disciplines) {
    this.disciplines = disciplines;
  }

  public Set<Image> getImages() {
    return images;
  }

  public void setImages(Set<Image> images) {
    this.images = images;
  }

  public Set<WorkDay> getWorkdays() {
    return workdays;
  }

  public void setWorkdays(Set<WorkDay> workdays) {
    this.workdays = workdays;
  }
}
