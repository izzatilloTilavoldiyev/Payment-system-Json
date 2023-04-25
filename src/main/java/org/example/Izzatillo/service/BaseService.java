package org.example.Izzatillo.service;

import org.example.Izzatillo.domain.DTO.Response;

public interface BaseService<T> {
    Response add(T t);
}
