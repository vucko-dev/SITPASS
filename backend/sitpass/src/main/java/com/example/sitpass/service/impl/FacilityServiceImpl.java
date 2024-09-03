package com.example.sitpass.service.impl;

import com.example.sitpass.dto.AccountRequestDTO;
import com.example.sitpass.dto.FacilityDTO;
import com.example.sitpass.dto.ImageDTO;
import com.example.sitpass.dto.WorkDayDTO;
import com.example.sitpass.enums.RequestStatus;
import com.example.sitpass.model.*;
import com.example.sitpass.repository.*;
import com.example.sitpass.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class FacilityServiceImpl implements FacilityService {

  @Autowired
  private FacilityRepository facilityRepository;

  @Autowired
  private DisciplineRepository disciplineRepository;

  @Autowired
  private ImageService  imageService;

  @Autowired
  private WorkDayRepository workDayRepository;

  @Autowired
  private WorkDayService workDayService;

  @Autowired
  private ManagesService managesService;

  @Autowired
  private UserService userService;

  @Autowired
  private ReviewService reviewService;

  @Autowired
  private ManagesRepository managesRepository;

  @Autowired
  private ExerciseRepository exerciseRepository;

  @Autowired
  private ExerciseService exerciseService;


  @Override
  public Facility getFacilityById(Long id) {
//    System.out.println(id);
    Facility facility =  facilityRepository.findById(id).orElseGet(null);
    User user = userService.getCurrentUser();
    String role = user.getRoles().get(0).getName();
    if(facility==null){
      throw new RuntimeException("No facility found");
    }
    if(role.equals("ADMIN") || facility.getActive()){
      return facility;
    } else{
      return null;
    }
  }

  @Override
  public Facility getFacilityByFacilityName(String facilityName) {
    Facility facility =  facilityRepository.findByName(facilityName);
    User user = userService.getCurrentUser();
    String role = user.getRoles().get(0).getName();
    if(facility==null){
      return null;
    }
    if(role.equals("ADMIN") || facility.getActive()){
      return facility;
    } else{
      return null;
    }
  }

  @Override
  public List<Facility> getFacilities() {
    List<Facility> allFacilities =  facilityRepository.findAll();
    User user = userService.getCurrentUser();
    String role = user.getRoles().get(0).getName();
    List<Facility> facilities = new ArrayList<>();
    if((role.equals("ADMIN"))) {
      facilities = allFacilities;
    }else{
      for(Facility facility : allFacilities) {
        if(facility.getActive()==true){
          facilities.add(facility);
        }
      }
    }
    return facilities;
  }

  @Override
  public Facility saveFacility(FacilityDTO facilityDTO) {
    Facility facility = new Facility();
    facility.setName(facilityDTO.getName());
    facility.setDescription(facilityDTO.getDescription());
    facility.setAddress(facilityDTO.getAddress());
    facility.setCity(facilityDTO.getCity());
    facility.setActive(facilityDTO.getActive());
    facility.setCreatedAt(LocalDate.now());
    facility.setTotalRating(facilityDTO.getTotalRating());
    Set<Discipline> disciplinesEntities = facilityDTO.getDisciplines().stream()
      .map(disciplineDTO -> disciplineRepository.findById(disciplineDTO.getId()).orElseThrow(() -> new RuntimeException("Discipline not found")))
      .collect(Collectors.toSet());
    facility.setDisciplines(disciplinesEntities);
    Set<WorkDayDTO> workDaysDTO = facilityDTO.getWorkDays();
    Set<WorkDay> workdays = new HashSet<>();
    if (workDaysDTO == null) {
      throw new IllegalArgumentException("workDaysDTO is null");
    }
    for (WorkDayDTO workDayDTO : workDaysDTO) {
      WorkDay workDay = workDayService.save(workDayDTO);
      workdays.add(workDay);
    }
    facility.setWorkdays(workdays);
    return facilityRepository.save(facility);
  }

  @Override
  public Facility updateFacility(Long id,FacilityDTO facilityDTO) throws RuntimeException {
    User user = userService.getCurrentUser();
    String role = user.getRoles().get(0).getName();
    if(!(role.equals("ADMIN")||(role.equals("MANAGER") && managesService.hasRightsToFacility(user.getId(),(long)facilityDTO.getId())))){
        throw new RuntimeException("Insufficient permission!");
    }
    Facility updatedFacility = facilityRepository.findById(id).orElseGet(null);
    updatedFacility.setName(facilityDTO.getName());
    updatedFacility.setDescription(facilityDTO.getDescription());
    updatedFacility.setAddress(facilityDTO.getAddress());
    updatedFacility.setCity(facilityDTO.getCity());
    updatedFacility.setActive(facilityDTO.getActive());
    updatedFacility.setCreatedAt(LocalDate.now());
    updatedFacility.setTotalRating(facilityDTO.getTotalRating());
    Set<Discipline> disciplinesEntities = facilityDTO.getDisciplines().stream()
      .map(disciplineDTO -> disciplineRepository.findById(disciplineDTO.getId()).orElseThrow(() -> new RuntimeException("Discipline not found")))
      .collect(Collectors.toSet());
    updatedFacility.setDisciplines(disciplinesEntities);
    Set<WorkDayDTO> wordDaysDTO = facilityDTO.getWorkDays();
    Set<WorkDay> workdays = new HashSet<>();
    for (WorkDayDTO workDayDTO : wordDaysDTO) {
      WorkDay workDay = workDayService.save(workDayDTO);
      workdays.add(workDay);
    }
        updatedFacility.setWorkdays(workdays);

//    Set<ImageDTO> imagesDTO = facilityDTO.getImages();
//    Set<Image> images = new HashSet<>();
//    for (ImageDTO imageDTO : imagesDTO) {
//      try {
//        Image image = imageService.save(imageDTO);
//        images.add(image);
//      } catch (IOException e) {
//        throw new RuntimeException(e);
//      }
//    }
//    updatedFacility.setImages(images);
    return this.facilityRepository.save(updatedFacility);
  }

  @Override
  public void deleteFacility(Long id) {
    if(!facilityRepository.existsById(id)) {
      throw new RuntimeException("Facility not found!");
    }
    Facility facility = facilityRepository.findById(id).orElseThrow(() -> new RuntimeException("Facility not found!"));

    List<Review> reviews = reviewService.getReviewsByFacilityId(id);
    for (Review review : reviews) {
      reviewService.deleteReviewById(review.getId());
    }

    List<Exercise> exercises = exerciseRepository.findByFacilityId(id);
    for (Exercise exercise : exercises) {
        exerciseService.deleteExercise(exercise.getId());
    }

    List<Manages> manages = managesRepository.findByFacilityId(id);
    for(Manages manage: manages){
      managesService.delete(manage.getId());
    }

    facilityRepository.deleteById(id);
    Set<Image> images = facility.getImages();
    for(Image image : images) {
      imageService.deleteById(image.getId());
    }
  }

  @Override
  public Facility addImagesToFacility(Long facilityId, List<ImageDTO> imageDTOs) throws IOException, IOException {
    Facility facility = facilityRepository.findById(facilityId).orElse(null);

    if (facility == null) {
      throw new RuntimeException("Facility not found");
    }

    User user = userService.getCurrentUser();
    String role = user.getRoles().get(0).getName();

    if(!(role.equals("ADMIN")||(role.equals("MANAGER") && managesService.hasRightsToFacility(user.getId(),facilityId)))){
      throw new RuntimeException("Insufficient permission!");
    }


    Set<Image> images = new HashSet<>(facility.getImages());

    for (ImageDTO imageDTO : imageDTOs) {
      Image image = imageService.save(imageDTO);
      images.add(image);
    }

    facility.setImages(images);
    return facilityRepository.save(facility);
  }

  @Override
  public Facility updateFacilityRating(Long id, Double rating){
    Facility facility = facilityRepository.findById(id).orElse(null);

    if (facility == null) {
      throw new RuntimeException("Facility not found");
    }

    facility.setTotalRating(rating);
    return this.facilityRepository.save(facility);
  }

//  @Override
//  public List<Facility> getFacilitiesByCityName(String cityName){
//    return facilityRepository.findByCity(cityName);
//  }

  @Override
  public Facility addImages(Long facilityId, List<MultipartFile>images){
    Facility facility = facilityRepository.findById(facilityId).orElse(null);
    if (facility == null) {
      throw new RuntimeException("Facility not found");
    }

    User user = userService.getCurrentUser();
    String role = user.getRoles().get(0).getName();

    if(!(role.equals("ADMIN")||(role.equals("MANAGER") && managesService.hasRightsToFacility(user.getId(),facilityId)))){
      throw new RuntimeException("Insufficient permission!");
    }
    Set<Image> imagesList = new HashSet<>();
    for (MultipartFile image : images) {
      try {
        ImageDTO imageDTO = new ImageDTO();
        imageDTO.setFile(image);
        Image imageEntity = imageService.save(imageDTO);
        imageEntity.setImage(image.getBytes());
        imagesList.add(imageEntity);
      } catch (IOException e) {
        throw new RuntimeException(e);
      }
    }
    facility.setImages(imagesList);
    return facilityRepository.save(facility);
  }

}
