package dev.thiagokoster.encrypt.dtos;

import java.util.UUID;

public record VaultResponse(UUID id, String name, String description) {}
