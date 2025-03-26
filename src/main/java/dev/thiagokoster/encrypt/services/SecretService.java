package dev.thiagokoster.encrypt.services;

import dev.thiagokoster.encrypt.dtos.SecretRequest;
import dev.thiagokoster.encrypt.dtos.SecretResponse;
import dev.thiagokoster.encrypt.models.Secret;
import dev.thiagokoster.encrypt.models.User;
import dev.thiagokoster.encrypt.models.Vault;
import dev.thiagokoster.encrypt.repositories.SecretRepository;
import org.springframework.stereotype.Service;

@Service
public class SecretService {
    private final SecretRepository secretRepository;

    public SecretService(SecretRepository secretRepository) {
        this.secretRepository = secretRepository;
    }

    public Secret create(User user, Vault vault, SecretRequest request) {
        String superEncryptedPass = request.password() + "__secret";

        Secret secret = new Secret(
                request.name(),
                request.url(),
                request.username(),
                superEncryptedPass,
                vault);

        return secretRepository.save(secret);
    }
}

