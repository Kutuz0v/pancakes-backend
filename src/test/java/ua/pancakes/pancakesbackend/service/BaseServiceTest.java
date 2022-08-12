package ua.pancakes.pancakesbackend.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import ua.pancakes.pancakesbackend.model.BaseEntity;
import ua.pancakes.pancakesbackend.repository.BaseRepository;

import java.util.ArrayList;
import java.util.Map;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

abstract class BaseServiceTest
        <E extends BaseEntity,
                R extends BaseRepository<E, Long>> {

    protected final Long NEW_ENTITY_ID = 1L;
    protected final Long FIND_ENTITY_BY_ID = 2L;
    protected final Long UPDATE_ENTITY_ID = 3L;
    protected final Long UPDATED_ENTITY = 4L;
    protected final Long DELETE_ENTITY_ID = 5L;

    protected Map<Long, E> entities = Map.of();

    @Autowired
    BaseService<E> service;

    @MockBean
    R repository;

    @BeforeEach
    void setUp() {
        initializeEntitiesMap();
        mockBaseRepository();
    }

    @Test
    void create() {
        E entity = entities.get(NEW_ENTITY_ID);
        E createdEntity = service.create(entity);

        assertEquals(entity, createdEntity);
    }

    @Test
    void get() {
        assertEquals(entities.get(FIND_ENTITY_BY_ID), service.get(FIND_ENTITY_BY_ID));
    }

    @Test
    void getAll() {
        assertEquals(new ArrayList<>(entities.values()), service.getAll());
    }

    @Test
    void update() {
        assertEquals(
                entities.get(UPDATE_ENTITY_ID),
                service.update(UPDATE_ENTITY_ID, entities.get(UPDATE_ENTITY_ID)));
        Mockito.verify(repository, Mockito.times(1)).existsById(UPDATE_ENTITY_ID);
    }

    @Test
    void delete() {
        service.delete(DELETE_ENTITY_ID);
        Mockito.verify(repository, Mockito.times(1)).existsById(DELETE_ENTITY_ID);
        Mockito.verify(repository, Mockito.times(1)).deleteById(DELETE_ENTITY_ID);
    }

    /**
     * Mocking {@link #repository}
     */
    protected void mockBaseRepository() {
        mockRepositoryToCreate();

        mockRepositoryToGet();

        mockRepositoryToGetAll();

        mockRepositoryToUpdate();

        mockRepositoryToDelete();
    }

    /**
     * For {@link #delete()} method
     */
    private void mockRepositoryToDelete() {
        Mockito.when(repository.existsById(DELETE_ENTITY_ID))
                .thenReturn(true);
        Mockito.doNothing().when(repository).deleteById(DELETE_ENTITY_ID);
    }

    /**
     * For {@link #update()} method
     */
    private void mockRepositoryToUpdate() {
        Mockito.when(repository.existsById(UPDATE_ENTITY_ID))
                .thenReturn(true);
        Mockito.when(repository.save(entities.get(UPDATE_ENTITY_ID)))
                .thenReturn(entities.get(UPDATE_ENTITY_ID));
    }

    /**
     * For {@link #getAll()} method
     */
    private void mockRepositoryToGetAll() {
        Mockito.when(repository.findAll())
                .thenReturn(entities.values().stream().toList());
    }

    /**
     * For {@link #get()} method
     */
    private void mockRepositoryToGet() {
        Mockito.when(repository.findById(FIND_ENTITY_BY_ID))
                .thenReturn(Optional.of(entities.get(FIND_ENTITY_BY_ID)));
    }

    /**
     * For {@link #create()} method
     */
    private void mockRepositoryToCreate() {
        Mockito.when(repository.save(entities.get(NEW_ENTITY_ID)))
                .thenReturn(entities.get(NEW_ENTITY_ID));
    }

    /**
     * Initializes entity for mocking {@link #repository}
     */
    protected void initializeEntitiesMap() {
        entities = Map.of(
                NEW_ENTITY_ID, getEntityForCreateTest(),
                FIND_ENTITY_BY_ID, getEntityForGetTest(),
                UPDATE_ENTITY_ID, getEntityForUpdateTest(),
                UPDATED_ENTITY, getUpdatedEntityForUpdateTest(),
                DELETE_ENTITY_ID, getEntityForDeleteTest()
        );
    }

    /**
     * Provide entity for {@link #create()} test
     */
    abstract E getEntityForCreateTest();

    /**
     * Provide entity for {@link #get()} test
     */
    abstract E getEntityForGetTest();

    /**
     * Provide entity for {@link #update()} test
     */
    abstract E getEntityForUpdateTest();

    /**
     * Provide updated entity for {@link #update()} test
     */
    abstract E getUpdatedEntityForUpdateTest();

    /**
     * Provide entity for {@link #delete()} test
     */
    abstract E getEntityForDeleteTest();
}