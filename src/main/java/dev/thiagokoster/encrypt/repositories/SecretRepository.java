package dev.thiagokoster.encrypt.repositories;

import dev.thiagokoster.encrypt.models.Secret;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface SecretRepository extends CrudRepository<Secret, UUID> {
}
