package com.example.sitpass.mapper;

import com.example.sitpass.dto.DisciplineDTO;
import com.example.sitpass.model.Discipline;
import org.springframework.stereotype.Component;

@Component
public class DisciplineMapper implements MapperInterface<Discipline, DisciplineDTO> {

  public Discipline toEntity(DisciplineDTO dto) {
    Discipline discipline = new Discipline();
    discipline.setId(dto.getId());
    discipline.setName(dto.getName());
    return discipline;
  }

  public DisciplineDTO toDto(Discipline entity) {
    DisciplineDTO dto = new DisciplineDTO();
    dto.setId(entity.getId());
    dto.setName(entity.getName());
    return dto;
  }
}
