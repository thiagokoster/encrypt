package dev.thiagokoster.encrypt.DTOs;

import java.sql.Timestamp;

public record LoginResponse(Timestamp expiresAt, String token) {
}
