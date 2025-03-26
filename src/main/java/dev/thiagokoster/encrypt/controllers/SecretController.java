package dev.thiagokoster.encrypt.controllers;

import dev.thiagokoster.encrypt.dtos.SecretRequest;
import dev.thiagokoster.encrypt.dtos.SecretResponse;
import dev.thiagokoster.encrypt.models.Secret;
import dev.thiagokoster.encrypt.models.User;
import dev.thiagokoster.encrypt.models.Vault;
import dev.thiagokoster.encrypt.services.SecretService;
import dev.thiagokoster.encrypt.services.VaultService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.UUID;

@RestController
@RequestMapping("/vaults/{vaultId}/secrets")
public class SecretController {

    private final VaultService vaultService;
    private final SecretService secretService;

    public SecretController(VaultService vaultService, SecretService secretService) {
        this.vaultService = vaultService;
        this.secretService = secretService;
    }

    @PostMapping
    public ResponseEntity<SecretResponse> create(
            @AuthenticationPrincipal User user,
            @PathVariable UUID vaultId,
            @RequestBody SecretRequest request
    ){

        //TODO: Create Secrets facade to handle model conversion and decryption
        Vault vault = vaultService.getById(user, vaultId);
        Secret secret = secretService.create(user, vault, request);
        SecretResponse response = new SecretResponse(secret.getId(),
                secret.getName(),
                secret.getUrl(),
                secret.getUsername(),
                "placeholder");

        URI location = URI.create(String.format("/vaults/%s/secrets/%s", vault.getId(), response.id()));
        return ResponseEntity.created(location).body(response);
    }
}
