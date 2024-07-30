package ru.telanors.rest.service;

import java.io.Serializable;
import java.util.List;

public interface CrudService<ID extends Serializable, T> {

    T findById(ID id);

    List<T> findAll();

    T save(T dto);

    T update(ID id, T dto);

    void delete(ID id);
}
