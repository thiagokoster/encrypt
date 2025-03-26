package dev.thiagokoster.encrypt.services;

import dev.thiagokoster.encrypt.dtos.CreateVaultRequest;
import dev.thiagokoster.encrypt.dtos.VaultResponse;
import dev.thiagokoster.encrypt.exceptions.ResourceNotFoundException;
import dev.thiagokoster.encrypt.models.User;
import dev.thiagokoster.encrypt.models.Vault;
import dev.thiagokoster.encrypt.repositories.VaultRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;


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

    public VaultResponse getById(User user, UUID vaultId) {
        Optional<Vault> vaultOpt = vaultRepository.findByIdAndUserId(vaultId, user.getId());

        if (vaultOpt.isEmpty()) {
            throw new ResourceNotFoundException(String.format("Vault '%s' not found", vaultId));
        }

        Vault vault = vaultOpt.get();

        return new VaultResponse(vault.getId(), vault.getName(), vault.getDescription());
    }

    public List<VaultResponse> getAll(User user) {
        return vaultRepository.findAllByUserId(user.getId()).stream().map((vault) ->
                new VaultResponse(vault.getId(), vault.getName(), vault.getDescription())).collect(Collectors.toList());
    }
}
