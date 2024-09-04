package com.example.sitpass.config.image;


import com.example.sitpass.model.Image;
import com.example.sitpass.model.User;
import com.example.sitpass.repository.ImageRepository;
import com.example.sitpass.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Component
public class ImageLoader implements CommandLineRunner {

  @Autowired
  private UserRepository userRepository;
  @Autowired
  private ImageRepository imageRepository;

  @Override
  @Transactional
  public void run(String... args) throws Exception {
    for (Image image : imageRepository.findAll()) {
      String imageName = deriveImageNameFromImage(image);
      Path imagePath = Paths.get("src/main/resources/static/images/" + imageName);
      byte[] imageBytes = Files.readAllBytes(imagePath);
      image.setImage(imageBytes);
      imageRepository.save(image);
    }
  }

  private String deriveImageNameFromImage(Image image) {
    return image.getId() + ".jpg";
  }
}

