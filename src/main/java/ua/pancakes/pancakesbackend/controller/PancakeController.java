package ua.pancakes.pancakesbackend.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ua.pancakes.pancakesbackend.model.Pancake;

@RequestMapping("/pancakes")
@RestController
@PreAuthorize("hasAuthority('USER')")
public class PancakeController extends BaseController<Pancake> {
}
