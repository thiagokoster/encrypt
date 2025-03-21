package dev.thiagokoster.encrypt.Controllers;

import dev.thiagokoster.encrypt.DTOs.CreateUserRequest;
import dev.thiagokoster.encrypt.DTOs.UserResponse;
import dev.thiagokoster.encrypt.Services.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UserResponse createUser(@Valid @RequestBody CreateUserRequest request){
        return userService.createUser(request);
    }
}
