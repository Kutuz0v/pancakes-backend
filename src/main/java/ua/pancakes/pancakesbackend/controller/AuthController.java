package ua.pancakes.pancakesbackend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.pancakes.pancakesbackend.payload.request.LoginRequest;
import ua.pancakes.pancakesbackend.payload.request.SignupRequest;
import ua.pancakes.pancakesbackend.service.ClientService;

import javax.validation.Valid;

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
                    loginRequest.getUsername(),
                    loginRequest.getPassword()
            )
    );
  }

  @PostMapping("/signup")
  public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signUpRequest) {
    return service.registerUser(signUpRequest);
  }
}
