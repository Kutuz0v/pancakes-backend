package ua.pancakes.pancakesbackend.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ua.pancakes.pancakesbackend.model.Client;
import ua.pancakes.pancakesbackend.model.dto.ClientDto;
import ua.pancakes.pancakesbackend.service.ClientService;

import java.util.List;

@Slf4j

@RequestMapping("/clients")
@RestController
@PreAuthorize("hasAnyAuthority('MODERATOR', 'ADMINISTRATOR', 'USER')")
public class ClientController {
    private final ClientService service;

    @Autowired
    public ClientController(ClientService service) {
        this.service = service;
    }

    @PostMapping
    public Client create(@RequestBody Client entity) {
        return service.create(entity);
    }

    @GetMapping("/{id}")
    public Client get(@PathVariable("id") Long id) {
        return service.get(id);
    }

    @GetMapping
    public List<Client> getAll() {
        return service.getAll();
    }

    @PutMapping("/{id}")
    public Client update(@PathVariable("id") Long id, @RequestBody Client entity) {
        return service.update(id, entity);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Long id) {
        service.delete(id);
    }
}
