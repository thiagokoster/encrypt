package dev.thiagokoster.encrypt.controllers;

import dev.thiagokoster.encrypt.dtos.CreateUserRequest;
import dev.thiagokoster.encrypt.dtos.UserResponse;
import dev.thiagokoster.encrypt.services.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<UserResponse> createUser(@Valid @RequestBody CreateUserRequest request){
        UserResponse response =  userService.createUser(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}
