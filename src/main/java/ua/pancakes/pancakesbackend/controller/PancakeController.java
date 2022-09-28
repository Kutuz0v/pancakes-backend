package ua.pancakes.pancakesbackend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ua.pancakes.pancakesbackend.model.Pancake;
import ua.pancakes.pancakesbackend.service.PancakeService;

import java.util.List;

@RequestMapping("/pancakes")
@RestController
public class PancakeController extends BaseController<Pancake> {
    @Autowired
    PancakeService service;

    @Override
    @GetMapping
    public List<Pancake> getAll() {
        return service.getAllForCurrentClient();
    }
}
