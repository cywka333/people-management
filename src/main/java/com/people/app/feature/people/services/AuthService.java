package com.people.app.feature.people.services;

import com.people.app.feature.people.auth.AuthResponse;
import com.people.app.feature.people.auth.AuthenticationRequest;
import com.people.app.feature.people.entities.Person;
import com.people.app.feature.people.repositories.PersonRepository;
import org.springframework.http.HttpStatusCode;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Service
public class AuthService {

    private final PersonRepository personRepository;

    private final PasswordEncoder passwordEncoder;

    public AuthService(PersonRepository personRepository, PasswordEncoder passwordEncoder) {
        this.personRepository = personRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public AuthResponse login(AuthenticationRequest authenticationRequest) {
        Optional<Person> response = this.personRepository.findUserByUsername(
                authenticationRequest.username());
        if (response.isPresent() && passwordEncoder.matches(authenticationRequest.password(), response.get().getPassword())) {
            return new AuthResponse(authenticationRequest.username());
        }
        throw new ResponseStatusException(HttpStatusCode.valueOf(401));
    }
}
