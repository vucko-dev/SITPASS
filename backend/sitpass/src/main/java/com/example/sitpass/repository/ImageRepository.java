package com.example.sitpass.repository;

import com.example.sitpass.model.Image;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImageRepository extends JpaRepository<Image, Long> {
  Image findById(long id);

  Image findByImage(byte[] image);
}
