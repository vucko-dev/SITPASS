package com.example.sitpass.mapper;

import java.io.IOException;

public interface MapperInterface<T,U> {

  T toEntity(U dto);
  U toDto(T entity);
}
