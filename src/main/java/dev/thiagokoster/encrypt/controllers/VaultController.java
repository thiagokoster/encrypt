package dev.thiagokoster.encrypt.controllers;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/vaults")
public class VaultController {

    @GetMapping
    public String index(
            @AuthenticationPrincipal UUID userId){
       return "Greetings from Spring Boot " + userId;
    }
}