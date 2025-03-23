package dev.thiagokoster.encrypt.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("/vaults")
public class VaultController {

    @GetMapping("/")
    public String index(){
       return "Greetings from Spring Boot";
    }
}
