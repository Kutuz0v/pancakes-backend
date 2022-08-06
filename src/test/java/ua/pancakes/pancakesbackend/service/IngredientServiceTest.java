package ua.pancakes.pancakesbackend.service;

import org.springframework.boot.test.context.SpringBootTest;
import ua.pancakes.pancakesbackend.entity.Ingredient;
import ua.pancakes.pancakesbackend.repository.IngredientRepository;

@SpringBootTest
class IngredientServiceTest extends BaseServiceTest<Ingredient, IngredientRepository> {

    @Override
    Ingredient getEntityForCreateTest() {
        return Ingredient.builder()
                .id(NEW_ENTITY_ID)
                .value("TestCreateValue")
                .price(11)
                .weight(50)
                .build();
    }

    @Override
    Ingredient getEntityForGetTest() {
        return Ingredient.builder()
                .id(FIND_ENTITY_BY_ID)
                .value("TestFindValue")
                .price(12)
                .weight(100)
                .build();
    }

    @Override
    Ingredient getEntityForUpdateTest() {
        return Ingredient.builder()
                .id(UPDATE_ENTITY_ID)
                .value("TestUpdateValue")
                .price(13)
                .weight(150)
                .build();
    }

    @Override
    Ingredient getUpdatedEntityForUpdateTest() {
        return Ingredient.builder()
                .id(UPDATED_ENTITY)
                .value("UpdatedValue")
                .price(14)
                .weight(200)
                .build();
    }

    @Override
    Ingredient getEntityForDeleteTest() {
        return Ingredient.builder()
                .id(DELETE_ENTITY_ID)
                .value("TestDeleteValue")
                .price(15)
                .weight(250)
                .build();
    }
}