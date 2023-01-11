package ua.pancakes.pancakesbackend.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("/")
public class HelloController {
    @GetMapping
    public String hello() {
        return "Application is running! Since 11.01.2023";
    }
}
