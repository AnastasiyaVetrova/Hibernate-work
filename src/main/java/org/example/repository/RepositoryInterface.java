package org.example.repository;

import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RepositoryInterface<T> {
    List<T> findAll() throws Exception;

    T findById(long id) throws Exception;

    void save(T t) throws Exception;

    void update(T t) throws Exception;

    void delete(long id) throws Exception;
}
