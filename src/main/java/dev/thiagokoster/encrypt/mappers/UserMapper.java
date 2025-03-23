package dev.thiagokoster.encrypt.mappers;

import dev.thiagokoster.encrypt.dtos.UserResponse;
import dev.thiagokoster.encrypt.models.User;

public class UserMapper {
    public static UserResponse toDTO(User user) {
       return new UserResponse(user.getId(), user.getEmail());
    }
}
