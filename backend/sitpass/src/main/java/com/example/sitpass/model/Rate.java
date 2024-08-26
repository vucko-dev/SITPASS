package com.example.sitpass.model;


import javax.persistence.*;

@Entity
@Table(name = "Rate")
public class Rate {

  @Id
  @Column(name="id")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name="equipment")
  private Integer equipment;

  @Column(name="stuff")
  private Integer stuff;

  @Column(name="hygiene")
  private Integer hygiene;

  @Column(name="space")
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

  public Integer getStuff() {
    return stuff;
  }

  public void setStuff(Integer stuff) {
    this.stuff = stuff;
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
