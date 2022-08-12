package ua.pancakes.pancakesbackend.service;


import org.springframework.validation.annotation.Validated;
import ua.pancakes.pancakesbackend.model.BaseEntity;

import javax.validation.Valid;
import javax.validation.constraints.Positive;
import java.util.List;

@Validated
public interface BaseService<T extends BaseEntity> {

    T create(@Valid T entity);

    T get(@Positive(message = "Id must be positive") Long id);

    List<T> getAll();

    T update(@Positive(message = "Id must be positive") Long id,
             @Valid T client);

    void delete(@Positive(message = "Id must be positive") Long id);
}
