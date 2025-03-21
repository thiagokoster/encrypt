package dev.thiagokoster.encrypt.Repositories;

import dev.thiagokoster.encrypt.Models.User;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface UserRepository extends CrudRepository<User, UUID> {
}
