package dev.thiagokoster.encrypt.dtos;

import java.util.UUID;

public record UserResponse(UUID id, String email) {
}
