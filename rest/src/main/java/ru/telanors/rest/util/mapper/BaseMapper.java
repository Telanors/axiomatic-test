package ru.telanors.rest.util.mapper;

import ru.telanors.rest.entity.BaseEntity;

import java.io.Serializable;

public interface BaseMapper<T extends BaseEntity<? extends Serializable>, F> {
    F toDTO(T entity);

    T toEntity(F dto);
}