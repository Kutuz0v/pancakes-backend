package ua.pancakes.pancakesbackend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.pancakes.pancakesbackend.payload.request.LoginRequest;
import ua.pancakes.pancakesbackend.payload.request.SignupRequest;
import ua.pancakes.pancakesbackend.service.ClientService;

import javax.validation.Valid;
import java.io.InvalidObjectException;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    ClientService service;

    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
        return ResponseEntity.ok(
                service.login(
                        loginRequest.getEmail(),
                        loginRequest.getPassword()
                )
        );
    }

    @GetMapping("/signin/google")
    public ResponseEntity<?> authenticateUserViaGoogle() throws InvalidObjectException {
        return ResponseEntity.ok(service.login());
    }

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signUpRequest) {
        return ResponseEntity.ok(service.registerUser(signUpRequest));
    }
}
