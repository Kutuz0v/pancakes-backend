package ua.pancakes.pancakesbackend.service.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import ua.pancakes.pancakesbackend.config.security.jwt.JwtUtils;
import ua.pancakes.pancakesbackend.model.Client;
import ua.pancakes.pancakesbackend.model.ERole;
import ua.pancakes.pancakesbackend.payload.googleauth.GoogleAuthenticationUtil.GoogleTokenRequestBody;
import ua.pancakes.pancakesbackend.payload.googleauth.GoogleAuthenticationUtil.GoogleTokenResponse;
import ua.pancakes.pancakesbackend.payload.googleauth.GoogleAuthenticationUtil.GoogleUser;
import ua.pancakes.pancakesbackend.payload.request.SignupRequest;
import ua.pancakes.pancakesbackend.payload.response.JwtResponse;
import ua.pancakes.pancakesbackend.repository.ClientRepository;
import ua.pancakes.pancakesbackend.repository.RoleRepository;
import ua.pancakes.pancakesbackend.service.ClientService;
import ua.pancakes.pancakesbackend.service.security.UserDetailsImpl;

import javax.servlet.http.HttpServletRequest;
import java.io.InvalidObjectException;
import java.util.List;
import java.util.Random;
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

    @Autowired
    HttpServletRequest request;

    @Override
    public Client registerUser(SignupRequest signUpRequest) {
        if (clientRepository.existsByEmail(signUpRequest.getEmail())) {
            // TODO: need throw exception
            throw new UnsupportedOperationException("Error: Email is already taken!");
        }

        return saveUserOnRegister(signUpRequest);
    }

    @Override
    public JwtResponse login(String email, String password) {
        authenticate(email, password);

        UserDetailsImpl userDetails =
                (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        return generateJwtResponse(userDetails);
    }

    @Override
    public JwtResponse login() throws InvalidObjectException {
        String googleCode = extractGoogleCodeFromRequest();

        GoogleUser googleUser = getGoogleUserByCode(googleCode);

        UserDetailsImpl userDetails = loadExistOrRegisterUser(googleUser);

        return generateJwtResponse(userDetails);
    }

    private Client saveUserOnRegister(SignupRequest signUpRequest) {
        return clientRepository.save(
                Client.builder()
                        .firstName(signUpRequest.getFirstName())
                        .lastName(signUpRequest.getLastName())
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
    }

    private JwtResponse generateJwtResponse(UserDetailsImpl userDetails) {
        String jwt = jwtUtils.generateJwtToken(userDetails.getEmail());

        List<String> roles = userDetails.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority).toList();

        return JwtResponse.builder()
                .accessToken(jwt)
                .id(userDetails.getId())
                .firstName(userDetails.getFirstName())
                .lastName(userDetails.getLastName())
                .email(userDetails.getEmail())
                .roles(roles)
                .build();
    }

    private UserDetailsImpl loadExistOrRegisterUser(GoogleUser googleUser) {
        return UserDetailsImpl.build(
                clientRepository.findByEmail(googleUser.getEmail())
                        .orElseGet(() -> saveUserOnRegister(new SignupRequest(
                                googleUser.getFirstName(),
                                googleUser.getLastName(),
                                googleUser.getEmail(),
                                null,
                                encoder.encode(generateRandomPassword())))
                        ));
    }

    private GoogleUser getGoogleUserByCode(String googleCode) {
        String GOOGLE_TOKEN_URI = "https://accounts.google.com/o/oauth2/token";
        WebClient googleClient = WebClient.create(GOOGLE_TOKEN_URI);

        GoogleTokenRequestBody body = new GoogleTokenRequestBody(googleCode);

        GoogleTokenResponse tokenResponse = googleClient.post()
                .body(Mono.just(body), GoogleTokenRequestBody.class)
                .retrieve()
                .bodyToMono(GoogleTokenResponse.class)
                .block();
        assert tokenResponse != null;

        String GOOGLE_USER_INFO_URI = "https://www.googleapis.com/oauth2/v1/userinfo";
        googleClient = googleClient.mutate()
                .baseUrl(GOOGLE_USER_INFO_URI)
                .defaultHeader("Authorization", String.format("Bearer %s", tokenResponse.getAccessToken()))
                .build();

        return googleClient.get()
                .retrieve()
                .bodyToMono(GoogleUser.class)
                .block();
    }

    private String extractGoogleCodeFromRequest() throws InvalidObjectException {
        String AUTHORIZATION_HEADER_NAME = "Authorization";
        String authHeader = request.getHeader(AUTHORIZATION_HEADER_NAME);

        String AUTHENTICATION_SCHEME = "GoogleCode ";
        if (!StringUtils.hasText(authHeader) || !authHeader.startsWith(AUTHENTICATION_SCHEME))
            throw new InvalidObjectException("Bad authorization header");

        return authHeader.substring(AUTHENTICATION_SCHEME.length());
    }

    private String generateRandomPassword() {
        String ALPHABET_OF_PASSWORD = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
        Random random = new Random();

        int PASSWORD_LENGTH = 120;
        StringBuilder passwordBuilder = new StringBuilder(PASSWORD_LENGTH);
        for (int i = 0; i < PASSWORD_LENGTH; i++) {
            passwordBuilder.append(
                    ALPHABET_OF_PASSWORD.charAt(
                            random.nextInt(ALPHABET_OF_PASSWORD.length()
                            )
                    )
            );
        }
        return passwordBuilder.toString();
    }

    private void authenticate(String username, String password) {
        SecurityContextHolder.getContext().setAuthentication(
                authenticationManager.authenticate(
                        new UsernamePasswordAuthenticationToken(username, password)
                )
        );
    }
}
