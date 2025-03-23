package dev.thiagokoster.encrypt.dtos;

import java.sql.Timestamp;

public record LoginResponse(Timestamp expiresAt, String token) {
}
