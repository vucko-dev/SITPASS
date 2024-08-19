package com.example.sitpass.service;

import com.example.sitpass.dto.ImageDTO;
import com.example.sitpass.model.Image;

import java.io.IOException;

public interface ImageService {
  Image findById(Long id);

  Image save(ImageDTO image) throws IOException;

  Image update(Long id, ImageDTO image) throws IOException;

  void deleteById(Long id);

}
