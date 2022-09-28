package ua.pancakes.pancakesbackend.service;

import org.springframework.boot.test.context.SpringBootTest;
import ua.pancakes.pancakesbackend.model.Client;
import ua.pancakes.pancakesbackend.repository.ClientRepository;

@SpringBootTest
class ClientServiceTest extends BaseServiceTest<Client, ClientRepository> {

    @Override
    Client getEntityForCreateTest() {
        return Client.builder()
                .id(NEW_ENTITY_ID)
                .firstName("TestCreateFirstName")
                .email("+380123456789")
                .build();
    }

    @Override
    Client getEntityForGetTest() {
        return Client.builder()
                .id(FIND_ENTITY_BY_ID)
                .firstName("TestFindFirstName")
                .lastName("TestFindLastName")
                .email("+380234567890")
                .build();
    }

    @Override
    Client getEntityForUpdateTest() {
        return Client.builder()
                .id(FIND_ENTITY_BY_ID)
                .firstName("TestUpdateFirstName")
                .lastName("TestUpdateLastName")
                .email("+380345678901")
                .build();
    }

    @Override
    Client getUpdatedEntityForUpdateTest() {
        return Client.builder()
                .id(FIND_ENTITY_BY_ID)
                .firstName("UpdatedFirstName")
                .lastName("UpdatedLastName")
                .email("+380456789012")
                .build();
    }

    @Override
    Client getEntityForDeleteTest() {
        return Client.builder()
                .id(DELETE_ENTITY_ID)
                .firstName("TestDeleteFirstName")
                .lastName("TestDeleteLastName")
                .email("+380567890123")
                .build();
    }
}