package ua.pancakes.pancakesbackend.repository;

import org.springframework.stereotype.Repository;
import ua.pancakes.pancakesbackend.entity.Ingredient;

@Repository
public interface IngredientRepository extends BaseRepository<Ingredient, Long> {
}
