package com.example.sitpass.model;

import javax.persistence.*;

@Entity
@Table(name="Discipline")
public class Discipline {

  private static final long serialVersionUID = 1L;

  @Id
  @Column(name="id")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  Long id;

  @Column(name="name")
  String name;

  public void setName(String name) {
    this.name = name;
  }

  public String getName() {
    return name;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

}
