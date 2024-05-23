package org.example.services;

import org.example.dto.PersonDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ServiceInterface<T> {
    List<T> getAll() throws Exception;
    T get(long id) throws Exception;
    void create(T t) throws Exception;
    void update(Long id, T t) throws Exception;
    void delete(Long id) throws Exception;
}
