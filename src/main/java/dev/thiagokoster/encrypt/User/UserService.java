package dev.thiagokoster.encrypt.User;

import com.password4j.Hash;
import com.password4j.Password;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserResponseDTO createUser(CreateUserRequestDTO request) {
        var hashedPassword = hashPassword(request.password());
        var user = new User(request.username(), request.email(), hashedPassword);

        userRepository.save(user);

        return UserMapper.toDTO(user);
    }

   private String hashPassword(String password) {
       return Password.hash(password).addRandomSalt().withScrypt().getResult();
   }

}
