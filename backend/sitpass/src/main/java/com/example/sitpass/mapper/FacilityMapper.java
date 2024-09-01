//package com.example.sitpass.mapper;
//
//import com.example.sitpass.dto.DisciplineDTO;
//import com.example.sitpass.dto.FacilityDTO;
//import com.example.sitpass.dto.ImageDTO;
//import com.example.sitpass.dto.WorkDayDTO;
//import com.example.sitpass.model.Discipline;
//import com.example.sitpass.model.Facility;
//import com.example.sitpass.model.Image;
//import com.example.sitpass.model.WorkDay;
//import org.apache.catalina.mapper.Mapper;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//import org.springframework.web.multipart.MultipartFile;
//import org.springframework.mock.web.MockMultipartFile;
//
//import java.io.IOException;
//import java.util.HashSet;
//import java.util.Set;
//
//@Component
//public class FacilityMapper implements Mapper<Facility, FacilityDTO> {
//  public FacilityMapper(){
//
//  }
//
//  @Autowired
//  private DisciplineMapper disciplineMapper;
//
//  public Facility toEntity(FacilityDTO facilityDTO) {
//    Facility facility = new Facility();
//    facility.setTotalRating(facility.getTotalRating());
//    facility.setName(facilityDTO.getName());
//    facility.setDescription(facilityDTO.getDescription());
//    Set<DisciplineDTO> disciplines = facilityDTO.getDisciplines();
//    Set<Discipline> disciplinesSet = new HashSet<>();
//    for(DisciplineDTO disciplineDTO : disciplines) {
//      disciplinesSet.add(disciplineMapper.toEntity(disciplineDTO));
//    }
//    facility.setDisciplines(disciplinesSet);
//    facility.setCreatedAt(facilityDTO.getCreatedAt());
//    Set<ImageDTO> images = facilityDTO.getImages();
//    Set<Image> imageSet = new HashSet<>();
//    for(ImageDTO imageDTO : images) {
//      Image image = new Image();
//      try {
//        image.setImage(imageDTO.getFile().getBytes());
//        image.setId(imageDTO.getId());
//        imageSet.add(image);
//      } catch (IOException e) {
//        throw new RuntimeException(e);
//      }
//    }
//    facility.setImages(imageSet);
//    facility.setActive(facilityDTO.getActive());
//    facility.setCity(facilityDTO.getCity());
//    facility.setDescription(facilityDTO.getDescription());
//    facility.setId(facility.getId());
//    Set<WorkDayDTO> workDays = facilityDTO.getWorkDays();
//    Set<WorkDay> workDaySet = new HashSet<>();
//    for(WorkDayDTO workDayDTO : workDays) {
//      WorkDay workDay = new WorkDay();
//      workDay.setId(workDayDTO.getId());
//      workDay.setDayOfWeek(workDayDTO.getDayOfWeek());
//      workDay.setFrom(workDayDTO.getFrom());
//      workDay.setUntil(workDayDTO.getUntil());
//      workDay.setValidFrom(workDayDTO.getValidFrom());
//      workDaySet.add(workDay);
//    }
//    facility.setWorkdays(workDaySet);
//    return facility;
//  }
//
//  public FacilityDTO toDto(Facility facility) {
//    FacilityDTO facilityDTO = new FacilityDTO();
//    facilityDTO.setActive(facility.getActive());
//    facilityDTO.setCity(facility.getCity());
//    facilityDTO.setDescription(facility.getDescription());
//    Set<Discipline> disciplines = facility.getDisciplines();
//    Set<DisciplineDTO> disciplinesSet = new HashSet<>();
//    for(Discipline disciplineDTO : disciplines) {
//      disciplinesSet.add(disciplineMapper.toDto(disciplineDTO));
//    }
//    facilityDTO.setDisciplines(disciplinesSet);
//    facilityDTO.setCreatedAt(facility.getCreatedAt());
//    Set<Image> images = facility.getImages();
//    Set<ImageDTO> imageSet = new HashSet<>();
//    for(Image image : images) {
//      ImageDTO imageDTO = new ImageDTO();
//      imageDTO.setId(image.getId());
//      MultipartFile file = new MockMultipartFile(fileName, fileName, contentType, bytes);
//      imageDTO.setFile(image.getImage());
//    }
//  }
//}
