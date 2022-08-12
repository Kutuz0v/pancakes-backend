package ua.pancakes.pancakesbackend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ua.pancakes.pancakesbackend.model.BaseEntity;
import ua.pancakes.pancakesbackend.service.BaseService;

import java.util.List;


public abstract class BaseController<T extends BaseEntity> {
    @Autowired
    BaseService<T> service;

    @PostMapping
    public T create(@RequestBody T entity) {
        return service.create(entity);
    }

    @GetMapping("/{id}")
    public T get(@PathVariable("id") Long id) {
        return service.get(id);
    }

    @GetMapping
    public List<T> getAll() {
        return service.getAll();
    }

    @PutMapping("/{id}")
    public T update(@PathVariable("id") Long id, @RequestBody T entity) {
        return service.update(id, entity);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Long id) {
        service.delete(id);
    }
}
