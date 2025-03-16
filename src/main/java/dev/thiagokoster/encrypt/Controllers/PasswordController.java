package dev.thiagokoster.encrypt.Controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("/vaults")
public class PasswordController {

    @GetMapping("/")
    public String index(){
       return "Greetings from Spring Boot";
    }
}
