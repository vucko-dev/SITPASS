package com.example.sitpass.service;

import com.example.sitpass.dto.WorkDayDTO;
import com.example.sitpass.model.WorkDay;

public interface WorkDayService {
    WorkDay findById(Long id);

    WorkDay save(WorkDayDTO workDayDTO);

    WorkDay update(WorkDayDTO workDayDTO);

    void delete(Long id);
}
