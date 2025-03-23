package dev.thiagokoster.encrypt.services;

import dev.thiagokoster.encrypt.dtos.CreateVaultRequest;
import dev.thiagokoster.encrypt.dtos.VaultResponse;
import dev.thiagokoster.encrypt.models.User;
import dev.thiagokoster.encrypt.models.Vault;
import dev.thiagokoster.encrypt.repositories.VaultRepository;
import org.springframework.stereotype.Service;


@Service
public class VaultService {
    private final VaultRepository vaultRepository;

    public VaultService (VaultRepository vaultRepository) {
        this.vaultRepository = vaultRepository;
    }

    public VaultResponse createVault(User user, CreateVaultRequest request) {
        Vault vault = new Vault(
                request.name(),
                request.description(),
                user);
        vaultRepository.save(vault);

        return new VaultResponse(vault.getId(),
                vault.getName(),
                vault.getDescription());
    }
}
