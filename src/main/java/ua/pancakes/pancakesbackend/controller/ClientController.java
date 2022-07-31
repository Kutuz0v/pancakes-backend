package ua.pancakes.pancakesbackend.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ua.pancakes.pancakesbackend.entity.Client;

@CrossOrigin
@RequestMapping("/clients")
@RestController
public class ClientController extends BaseController<Client>{
}
