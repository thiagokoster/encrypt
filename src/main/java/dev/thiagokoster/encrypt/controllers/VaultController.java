package dev.thiagokoster.encrypt.controllers;

import dev.thiagokoster.encrypt.dtos.CreateVaultRequest;
import dev.thiagokoster.encrypt.dtos.VaultResponse;
import dev.thiagokoster.encrypt.models.User;
import dev.thiagokoster.encrypt.models.Vault;
import dev.thiagokoster.encrypt.services.VaultService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/vaults")
public class VaultController {
    private final VaultService vaultService;

    public VaultController(VaultService vaultService) {
        this.vaultService = vaultService;
    }

    @GetMapping
    public ResponseEntity<List<VaultResponse>> getAll(
            @AuthenticationPrincipal User user){
        List<VaultResponse> vaults = vaultService.getAll(user);
       return ResponseEntity.ok(vaults);
    }

    @GetMapping("/{vaultId}")
    public ResponseEntity<VaultResponse> get(
            @AuthenticationPrincipal User user,
            @PathVariable("vaultId") UUID vaultId){
        //TODO: I don't want Models in my controllers,
        // create an Facade layer that works as a glue between controllers and services
        Vault vault = vaultService.getById(user, vaultId);
        VaultResponse response = new VaultResponse(
                vault.getId(),
                vault.getName(),
                vault.getDescription());
        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<VaultResponse> createVault(
            @AuthenticationPrincipal User user,
            @Valid @RequestBody CreateVaultRequest request
            ) {
         VaultResponse response = vaultService.createVault(user, request);
         URI location = URI.create("/vaults/" + response.id());
         return ResponseEntity.created(location).body(response);
    }
}