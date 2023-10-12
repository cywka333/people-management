package com.people.app.feature.people.auth;

import com.people.app.feature.people.services.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/auth")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class AuthenticationController {

    private final AuthService authService;

    @PostMapping("login")
    public AuthResponse login(@RequestBody AuthenticationRequest user) {
        return authService.login(user);
    }
}
