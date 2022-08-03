package ua.pancakes.pancakesbackend.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ua.pancakes.pancakesbackend.entity.Client;

@RequestMapping("/clients")
@RestController
public class ClientController extends BaseController<Client>{
}
