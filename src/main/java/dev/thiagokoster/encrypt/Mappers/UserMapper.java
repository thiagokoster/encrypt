package dev.thiagokoster.encrypt.Mappers;

import dev.thiagokoster.encrypt.DTOs.UserResponse;
import dev.thiagokoster.encrypt.Models.User;

public class UserMapper {
    public static UserResponse toDTO(User user) {
       return new UserResponse(user.getId(), user.getEmail());
    }
}
