package dev.thiagokoster.encrypt.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record CreateUserRequest(
        @NotBlank(message="Username is required")
        String username,

        @NotBlank(message="Email is required")
        @Email
        String email,

        @NotBlank(message="Password is required")
        @Size(min=6, message="Password must be at least 6 characters")
        String password
) {}
