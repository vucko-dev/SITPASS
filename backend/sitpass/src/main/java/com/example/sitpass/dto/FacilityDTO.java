package com.example.sitpass.dto;

import java.time.LocalDate;
import java.util.Set;

public class FacilityDTO {

  private int id;

  private String name;

  private String address;

  private String description;

  private LocalDate createdAt;

  public String city;

  public Double totalRating;

  public Boolean active;

  private Set<DisciplineDTO> disciplines;

  private Set<ImageDTO> images;

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getAddress() {
    return address;
  }

  public void setAddress(String address) {
    this.address = address;
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

  public Set<DisciplineDTO> getDisciplines() {
    return disciplines;
  }

  public void setDisciplines(Set<DisciplineDTO> disciplines) {
    this.disciplines = disciplines;
  }

  public Set<ImageDTO> getImages() {
    return images;
  }

  public void setImages(Set<ImageDTO> images) {
    this.images = images;
  }
}
