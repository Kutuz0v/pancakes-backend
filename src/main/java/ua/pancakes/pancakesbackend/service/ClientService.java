package ua.pancakes.pancakesbackend.service;

import ua.pancakes.pancakesbackend.model.Client;
import ua.pancakes.pancakesbackend.payload.request.SignupRequest;
import ua.pancakes.pancakesbackend.payload.response.JwtResponse;

import java.io.InvalidObjectException;


public interface ClientService extends BaseService<Client> {
    JwtResponse login(String username, String password);

    Client registerUser(SignupRequest signUpRequest);

    JwtResponse login() throws InvalidObjectException;
}
