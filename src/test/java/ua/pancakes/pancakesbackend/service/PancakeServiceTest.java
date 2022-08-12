package ua.pancakes.pancakesbackend.service;

import org.springframework.boot.test.context.SpringBootTest;
import ua.pancakes.pancakesbackend.model.Ingredient;
import ua.pancakes.pancakesbackend.model.Pancake;
import ua.pancakes.pancakesbackend.repository.PancakeRepository;

import java.util.Set;

@SpringBootTest
class PancakeServiceTest extends BaseServiceTest<Pancake, PancakeRepository> {
    @Override
    Pancake getEntityForCreateTest() {
        return Pancake.builder()
                .price(100)
                .weight(100)
                .ingredients(Set.of(
                        Ingredient.builder()
                                .id(1L)
                                .value("Value1")
                                .build(),
                        Ingredient.builder()
                                .id(2L)
                                .value("Value2")
                                .build()
                ))
                .build();
    }

    @Override
    Pancake getEntityForGetTest() {
        return Pancake.builder()
                .price(110)
                .weight(110)
                .ingredients(Set.of(
                        Ingredient.builder()
                                .id(1L)
                                .value("Value1")
                                .build(),
                        Ingredient.builder()
                                .id(2L)
                                .value("Value2")
                                .build()
                ))
                .build();
    }

    @Override
    Pancake getEntityForUpdateTest() {
        return Pancake.builder()
                .price(120)
                .weight(120)
                .ingredients(Set.of(
                        Ingredient.builder()
                                .id(1L)
                                .value("Value1")
                                .build(),
                        Ingredient.builder()
                                .id(2L)
                                .value("Value2")
                                .build()
                ))
                .build();
    }

    @Override
    Pancake getUpdatedEntityForUpdateTest() {
        return Pancake.builder()
                .price(130)
                .weight(130)
                .ingredients(Set.of(
                        Ingredient.builder()
                                .id(1L)
                                .value("Value1")
                                .build(),
                        Ingredient.builder()
                                .id(2L)
                                .value("Value2")
                                .build()
                ))
                .build();
    }

    @Override
    Pancake getEntityForDeleteTest() {
        return Pancake.builder()
                .price(140)
                .weight(140)
                .ingredients(Set.of(
                        Ingredient.builder()
                                .id(1L)
                                .value("Value1")
                                .build(),
                        Ingredient.builder()
                                .id(2L)
                                .value("Value2")
                                .build()
                ))
                .build();
    }
}
