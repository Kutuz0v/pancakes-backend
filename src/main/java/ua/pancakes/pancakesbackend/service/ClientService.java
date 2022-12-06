package ua.pancakes.pancakesbackend.service;

import ua.pancakes.pancakesbackend.model.Client;
import ua.pancakes.pancakesbackend.model.dto.ClientDto;
import ua.pancakes.pancakesbackend.payload.request.SignupRequest;
import ua.pancakes.pancakesbackend.payload.response.JwtResponse;

import java.io.InvalidObjectException;


public interface ClientService extends BaseService<Client> {
//    Client update(Long id, ClientDto client);

    JwtResponse login(String username, String password);

    Client registerUser(SignupRequest signUpRequest);

    JwtResponse login() throws InvalidObjectException;
}
