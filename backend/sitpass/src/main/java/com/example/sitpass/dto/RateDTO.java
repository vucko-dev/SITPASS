package com.example.sitpass.dto;

public class RateDTO {

  private Long id;

  private Integer equipment;

  private Integer staff;

  private Integer hygiene;

  private Integer space;


  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Integer getEquipment() {
    return equipment;
  }

  public void setEquipment(Integer equipment) {
    this.equipment = equipment;
  }

  public Integer getStaff() {
    return staff;
  }

  public void setStaff(Integer staff) {
    this.staff = staff;
  }

  public Integer getHygiene() {
    return hygiene;
  }

  public void setHygiene(Integer hygiene) {
    this.hygiene = hygiene;
  }

  public Integer getSpace() {
    return space;
  }

  public void setSpace(Integer space) {
    this.space = space;
  }
}
