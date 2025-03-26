package dev.thiagokoster.encrypt.repositories;

import dev.thiagokoster.encrypt.models.Vault;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface VaultRepository extends CrudRepository<Vault, UUID> {
    @Query("SELECT v FROM Vault v WHERE v.user.id = :userId")
    List<Vault> findAllByUserId(@Param("userId") UUID userId);

    @Query("SELECT v FROM Vault v WHERE v.id = :id AND v.user.id = :userId")
    Optional<Vault> findByIdAndUserId(
            @Param("id") UUID id,
            @Param("userId") UUID userId);
}
