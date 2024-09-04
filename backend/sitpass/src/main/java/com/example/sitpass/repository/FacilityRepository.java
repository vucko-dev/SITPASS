package com.example.sitpass.repository;

import com.example.sitpass.model.Facility;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FacilityRepository  extends JpaRepository<Facility, Long> {
    Facility findByName(String name);
}
