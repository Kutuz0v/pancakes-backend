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
                .price(25)
                .weight(150)
                .ingredients(Set.of(
                        Ingredient.builder()
                                .id(1L)
                                .price(10)
                                .weight(50)
                                .value("Value1")
                                .build(),
                        Ingredient.builder()
                                .id(2L)
                                .price(15)
                                .weight(100)
                                .value("Value2")
                                .build()
                ))
                .build();
    }

    @Override
    Pancake getEntityForGetTest() {
        return Pancake.builder()
                .price(50)
                .weight(300)
                .ingredients(Set.of(
                        Ingredient.builder()
                                .id(1L)
                                .price(20)
                                .weight(100)
                                .value("Value1")
                                .build(),
                        Ingredient.builder()
                                .id(2L)
                                .price(30)
                                .weight(200)
                                .value("Value2")
                                .build()
                ))
                .build();
    }

    @Override
    Pancake getEntityForUpdateTest() {
        return Pancake.builder()
                .price(75)
                .weight(450)
                .ingredients(Set.of(
                        Ingredient.builder()
                                .id(1L)
                                .price(30)
                                .weight(150)
                                .value("Value1")
                                .build(),
                        Ingredient.builder()
                                .id(2L)
                                .price(45)
                                .weight(300)
                                .value("Value2")
                                .build()
                ))
                .build();
    }

    @Override
    Pancake getUpdatedEntityForUpdateTest() {
        return Pancake.builder()
                .price(90)
                .weight(600)
                .ingredients(Set.of(
                        Ingredient.builder()
                                .id(1L)
                                .price(40)
                                .weight(200)
                                .value("Value1")
                                .build(),
                        Ingredient.builder()
                                .id(2L)
                                .price(50)
                                .weight(400)
                                .value("Value2")
                                .build()
                ))
                .build();
    }

    @Override
    Pancake getEntityForDeleteTest() {
        return Pancake.builder()
                .price(125)
                .weight(750)
                .ingredients(Set.of(
                        Ingredient.builder()
                                .id(1L)
                                .price(50)
                                .weight(250)
                                .value("Value1")
                                .build(),
                        Ingredient.builder()
                                .id(2L)
                                .price(75)
                                .weight(500)
                                .value("Value2")
                                .build()
                ))
                .build();
    }
}
