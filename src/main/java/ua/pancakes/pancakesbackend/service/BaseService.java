package ua.pancakes.pancakesbackend.service;


import ua.pancakes.pancakesbackend.entity.BaseEntity;

import java.util.List;

public interface BaseService<T extends BaseEntity> {

    T create(T entity);

    T get(Long id);

    List<T> getAll();

    T update(Long id, T client);

    void delete(Long id);
}
