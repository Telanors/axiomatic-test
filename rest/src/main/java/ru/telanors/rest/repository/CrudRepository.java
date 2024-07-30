package ru.telanors.rest.repository;

import ru.telanors.rest.entity.BaseEntity;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

public interface CrudRepository<ID extends Serializable, T extends BaseEntity<ID>> {
    T save(T entity);

    T update(ID id, T entity);

    void delete(T entity);

    Optional<T> findById(ID id);

    List<T> findAll();
}
