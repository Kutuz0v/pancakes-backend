package ua.pancakes.pancakesbackend.service;

import org.springframework.http.ResponseEntity;
import ua.pancakes.pancakesbackend.model.Client;
import ua.pancakes.pancakesbackend.payload.request.SignupRequest;
import ua.pancakes.pancakesbackend.payload.response.JwtResponse;


public interface ClientService extends BaseService<Client> {
    JwtResponse login(String username, String password);

    ResponseEntity<?> registerUser(SignupRequest signUpRequest);
}
