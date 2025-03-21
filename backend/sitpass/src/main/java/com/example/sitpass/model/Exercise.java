package com.example.sitpass.model;

import com.example.sitpass.dto.FacilityDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;




//Zbog Lombok-a ne trebaju geteri i seteri
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "exercises")
public class Exercise {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne
  @JoinColumn(name = "user_id", nullable = false)
  private User user;

  @Column(name = "from_time")
  private LocalDateTime from;

  @Column(name = "until_time")
  private LocalDateTime until;

  @ManyToOne
  @JoinColumn(name = "facility_id", nullable = false)
  private Facility facility;

}
