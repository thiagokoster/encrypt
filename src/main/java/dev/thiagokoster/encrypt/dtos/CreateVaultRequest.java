package dev.thiagokoster.encrypt.dtos;

import jakarta.validation.constraints.NotBlank;

public record CreateVaultRequest(
        @NotBlank(message = "Vault name is required")
        String name,
        String description
) {}
