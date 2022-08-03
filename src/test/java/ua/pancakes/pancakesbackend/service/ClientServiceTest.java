package ua.pancakes.pancakesbackend.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import ua.pancakes.pancakesbackend.entity.Client;
import ua.pancakes.pancakesbackend.repository.ClientRepository;

import java.util.ArrayList;
import java.util.Map;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class ClientServiceTest {

    private final Long NEW_CLIENT_ID = 1L;
    private final Long FIND_BY_ID = 2L;
    private final Long UPDATE_ID = 3L;
    private final Long DELETE_ID = 4L;

    private Map<Long, Client> clients;

    @MockBean
    ClientRepository repository;

    @Autowired
    ClientService service;

    @BeforeEach
    void setUp() {
        clients = Map.of(
                NEW_CLIENT_ID,
                Client.builder()
                        .id(NEW_CLIENT_ID)
                        .name("TestCreateName")
                        .phoneNumber("+380123456789")
                        .build(),
                FIND_BY_ID,
                Client.builder()
                        .id(FIND_BY_ID)
                        .name("TestFindName")
                        .phoneNumber("+380234567890")
                        .build(),
                UPDATE_ID,
                Client.builder()
                        .id(FIND_BY_ID)
                        .name("TestUpdateName")
                        .phoneNumber("+380345678901")
                        .build(),
                DELETE_ID,
                Client.builder()
                        .id(DELETE_ID)
                        .name("TestDeleteName")
                        .phoneNumber("+380456789012")
                        .build()
        );

        Mockito.when(repository.save(clients.get(NEW_CLIENT_ID)))
                .thenReturn(clients.get(NEW_CLIENT_ID));
        Mockito.when(repository.findById(FIND_BY_ID))
                .thenReturn(Optional.of(clients.get(FIND_BY_ID)));
        Mockito.when(repository.findAll())
                .thenReturn(clients.values().stream().toList());
        Mockito.when(repository.existsById(UPDATE_ID))
                .thenReturn(true);
        Mockito.when(repository.save(clients.get(UPDATE_ID)))
                .thenReturn(clients.get(UPDATE_ID));
        Mockito.when(repository.existsById(DELETE_ID))
                .thenReturn(true);
        Mockito.doNothing().when(repository).delete(clients.get(DELETE_ID));
    }

    @Test
    @DisplayName("Create new client")
    public void create() {
        Client client = clients.get(NEW_CLIENT_ID);

        Client createdClient = service.create(client);
        client.setId(createdClient.getId());    // No matter what ID was passed, repository will define new ID

        assertEquals(client, createdClient);
    }

    @Test
    @DisplayName("Get client by id")
    public void get() {
        assertEquals(clients.get(FIND_BY_ID), service.get(FIND_BY_ID));
    }

    @Test
    @DisplayName("Get all clients")
    public void getAll() {
        assertEquals(new ArrayList<>(clients.values()), service.getAll());
    }

    @Test
    @DisplayName("Update client")
    public void update() {
        assertEquals(
                clients.get(UPDATE_ID),
                service.update(UPDATE_ID, clients.get(UPDATE_ID)));
    }

    @Test
    @DisplayName("Delete client")
    public void delete() {
        service.delete(DELETE_ID);
        Mockito.verify(repository, Mockito.times(1)).deleteById(DELETE_ID);
    }
}