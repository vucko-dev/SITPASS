package com.example.sitpass.service.impl;

import com.example.sitpass.dto.ImageDTO;
import com.example.sitpass.model.Facility;
import com.example.sitpass.model.Image;
import com.example.sitpass.repository.ImageRepository;
import com.example.sitpass.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class ImageServiceImpl implements ImageService {

  @Autowired
  ImageRepository imageRepository;

  public Image findById(Long id) {
    return imageRepository.findById(id).orElseGet(null);
  }

  public Image save(ImageDTO imageDTO) throws IOException {
    Image image = new Image();
    image.setImage(imageDTO.getFile().getBytes());
    return imageRepository.save(image);
  }

  public Image update(Long id, ImageDTO imageDTO) throws IOException {
    Image image = imageRepository.findById(id).orElseGet(null);
    image.setImage(imageDTO.getFile().getBytes());
    return this.imageRepository.save(image);
  }

  public void deleteById(Long id) {
    if(!imageRepository.existsById(id)) {
      throw new RuntimeException("Facility not found!");
    }
    Image image = imageRepository.findById(id).orElseThrow(() -> new RuntimeException("Image not found!"));

    imageRepository.deleteById(id);
  }
}
