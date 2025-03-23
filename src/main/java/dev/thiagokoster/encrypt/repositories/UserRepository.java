package dev.thiagokoster.encrypt.repositories;

import dev.thiagokoster.encrypt.models.User;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends CrudRepository<User, UUID> {
    Optional<User> findByUsernameOrEmail(String username, String email);
}
