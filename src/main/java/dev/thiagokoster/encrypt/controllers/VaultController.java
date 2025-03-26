package dev.thiagokoster.encrypt.controllers;

import dev.thiagokoster.encrypt.dtos.CreateVaultRequest;
import dev.thiagokoster.encrypt.dtos.VaultResponse;
import dev.thiagokoster.encrypt.models.User;
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
        VaultResponse response = vaultService.getById(user, vaultId);
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