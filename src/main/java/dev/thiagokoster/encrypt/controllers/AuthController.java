package dev.thiagokoster.encrypt.controllers;

import dev.thiagokoster.encrypt.dtos.LoginRequest;
import dev.thiagokoster.encrypt.dtos.LoginResponse;
import dev.thiagokoster.encrypt.services.AuthService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/login")
    public LoginResponse login(@Valid @RequestBody LoginRequest request) {
        return authService.login(request);
    }
}
