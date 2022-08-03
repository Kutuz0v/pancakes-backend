package ua.pancakes.pancakesbackend.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ua.pancakes.pancakesbackend.entity.Ingredient;

@RequestMapping("/ingredients")
@RestController
public class IngredientController extends BaseController<Ingredient> {
}
