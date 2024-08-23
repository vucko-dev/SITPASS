package com.example.sitpass.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.sitpass.model.Exercise;

import java.util.List;

public interface ExerciseRepository extends JpaRepository<Exercise, Long> {
    List<Exercise> findByUserId(Long userId);
}
