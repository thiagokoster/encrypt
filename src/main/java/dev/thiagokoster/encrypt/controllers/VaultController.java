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

@RestController
@RequestMapping("/vaults")
public class VaultController {
    private final VaultService vaultService;

    public VaultController(VaultService vaultService) {
        this.vaultService = vaultService;
    }

    @GetMapping
    public String index(
            @AuthenticationPrincipal User user){
       return "Greetings from Spring Boot " + user.getId();
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