package ua.pancakes.pancakesbackend.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ua.pancakes.pancakesbackend.model.Client;

@RequestMapping("/clients")
@RestController
@PreAuthorize("hasAnyAuthority('MODERATOR', 'ADMINISTRATOR')")
public class ClientController extends BaseController<Client> {
}
