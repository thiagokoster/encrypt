package dev.thiagokoster.encrypt.dtos;

import jakarta.validation.constraints.NotBlank;

public record SecretRequest(
        String name,
        String url,
        String username,
        String password
        ) {}
