package ua.pancakes.pancakesbackend.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ua.pancakes.pancakesbackend.entity.Pancake;

@RequestMapping("/pancakes")
@RestController
public class PancakeController extends BaseController<Pancake> {
}
