package dev.thiagokoster.encrypt.User;

public class UserMapper {
    public static UserResponseDTO toDTO(User user) {
       return new UserResponseDTO(user.getId(), user.getEmail());
    }
}
