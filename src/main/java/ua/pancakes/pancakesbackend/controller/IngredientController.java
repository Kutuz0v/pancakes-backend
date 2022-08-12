package ua.pancakes.pancakesbackend.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ua.pancakes.pancakesbackend.model.Ingredient;

@RequestMapping("/ingredients")
@RestController
@PreAuthorize("hasAuthority('MODERATOR') or hasAuthority('ADMIN')")
public class IngredientController extends BaseController<Ingredient> {
}
