package dev.thiagokoster.encrypt.repositories;

import dev.thiagokoster.encrypt.models.Vault;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface VaultRepository extends CrudRepository<Vault, UUID> {
}
