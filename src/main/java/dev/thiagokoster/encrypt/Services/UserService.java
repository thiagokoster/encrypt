package dev.thiagokoster.encrypt.Services;

import dev.thiagokoster.encrypt.DTOs.CreateUserRequest;
import dev.thiagokoster.encrypt.DTOs.UserResponse;
import dev.thiagokoster.encrypt.Repositories.UserRepository;
import dev.thiagokoster.encrypt.Exceptions.InvalidUserException;
import dev.thiagokoster.encrypt.Models.User;
import dev.thiagokoster.encrypt.Mappers.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;


@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private CryptoService cryptoService;

    public UserResponse createUser(CreateUserRequest request) {
        String hashedPassword = cryptoService.hashPassword(request.password());
        User user = new User(request.username(), request.email(), hashedPassword);
        try {
            userRepository.save(user);
        } catch (DataIntegrityViolationException e) {
            if (e.getMessage().contains("users_username_key")) {
                throw new InvalidUserException(String.format("Username '%s' already exists", request.username()));
            } else if (e.getMessage().contains("users_email_key")) {
                throw new InvalidUserException(String.format("Email '%s' already in use", request.email()));
            }
        }

        return UserMapper.toDTO(user);
    }
}
