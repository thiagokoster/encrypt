package dev.thiagokoster.encrypt.User;

import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserResponseDTO createUser(CreateUserRequestDTO request) {
        var password = request.password();
        var user = new User(request.username(), request.email(), password);

        userRepository.save(user);

        return UserMapper.toDTO(user);
    }

}
