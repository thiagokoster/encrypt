package dev.thiagokoster.encrypt.dtos;

import java.util.UUID;

public record SecretResponse (UUID id,
                              String name,
                              String url,
                              String username,
                              String password) {}
