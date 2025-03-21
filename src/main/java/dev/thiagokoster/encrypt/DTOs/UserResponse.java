package dev.thiagokoster.encrypt.DTOs;

import java.util.UUID;

public record UserResponse(UUID id, String email) {
}
