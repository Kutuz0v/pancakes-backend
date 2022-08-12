package ua.pancakes.pancakesbackend.service.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ua.pancakes.pancakesbackend.config.security.jwt.JwtUtils;
import ua.pancakes.pancakesbackend.model.Client;
import ua.pancakes.pancakesbackend.model.ERole;
import ua.pancakes.pancakesbackend.payload.request.SignupRequest;
import ua.pancakes.pancakesbackend.payload.response.JwtResponse;
import ua.pancakes.pancakesbackend.payload.response.MessageResponse;
import ua.pancakes.pancakesbackend.repository.ClientRepository;
import ua.pancakes.pancakesbackend.repository.RoleRepository;
import ua.pancakes.pancakesbackend.service.ClientService;
import ua.pancakes.pancakesbackend.service.security.UserDetailsImpl;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class ClientServiceImpl
        extends BaseServiceImpl<Client>
        implements ClientService {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    JwtUtils jwtUtils;

    @Autowired
    ClientRepository clientRepository;

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    RoleRepository roleRepository;

    @Override
    public ResponseEntity<?> registerUser(SignupRequest signUpRequest) {
        if (clientRepository.existsByUsername(signUpRequest.getUsername())) {
            // TODO: need throw exception
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Username is already taken!"));
        }

        if (clientRepository.existsByEmail(signUpRequest.getEmail())) {
            // TODO: need throw exception
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Email is already in use!"));
        }

        // Create new user's account
        clientRepository.save(
                Client.builder()
                        .username(signUpRequest.getUsername())
                        .email(signUpRequest.getEmail())
                        .password(encoder.encode(signUpRequest.getPassword()))
                        .roles(signUpRequest.getRole() == null ?
                                Set.of(roleRepository.findByName(ERole.USER).get()) :
                                signUpRequest.getRole().stream().map(
                                        (role) -> roleRepository.findByName(ERole.valueOf(role.toUpperCase()))
                                                .orElseThrow(
                                                        () -> new IllegalArgumentException("Error: Role is not found.")
                                                )
                                ).collect(Collectors.toSet()))
                        .build()
        );

        return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
    }

    @Override
    public JwtResponse login(String username, String password) {
        authenticate(username, password);

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String jwt = jwtUtils.generateJwtToken(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        List<String> roles = userDetails.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority).toList();

        return JwtResponse.builder()
                .accessToken(jwt)
                .id(userDetails.getId())
                .username(userDetails.getUsername())
                .email(userDetails.getEmail())
                .roles(roles)
                .build();
    }

    void authenticate(String username, String password) {
        SecurityContextHolder.getContext().setAuthentication(
                authenticationManager.authenticate(
                        new UsernamePasswordAuthenticationToken(username, password)
                )
        );
    }
}
