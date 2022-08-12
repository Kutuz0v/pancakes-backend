package ua.pancakes.pancakesbackend.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import ua.pancakes.pancakesbackend.model.BaseEntity;
import ua.pancakes.pancakesbackend.repository.BaseRepository;
import ua.pancakes.pancakesbackend.service.BaseService;

import java.util.List;

public abstract class BaseServiceImpl<T extends BaseEntity>
        implements BaseService<T> {

    @Autowired
    protected BaseRepository<T, Long> repository;

    @Override
    public T create(T entity) {
        return save(entity);
    }

    @Override
    public T get(Long id) {
        return repository
                .findById(id)
                // TODO: throw custom exception (EntityNotFoundException)
                .orElseThrow(() -> new RuntimeException("EntityNotFoundException"));
    }

    @Override
    public List<T> getAll() {
        return repository.findAll();
    }

    @Override
    public T update(Long id, T entity) {
        if (repository.existsById(id))
            return save(id, entity);
            // TODO: throw custom exception (EntityNotFoundException)
        else throw new RuntimeException("EntityNotFoundException");
    }

    @Override
    public void delete(Long id) {
        if (repository.existsById(id))
            repository.deleteById(id);
        else throw new RuntimeException("EntityNotFoundException with id " + id);

    }

    private T save(T entity) {
        return save(0L, entity);
    }

    private T save(Long id, T entity) {
        entity.setId(id);
        return repository.save(entity);
    }
}
