package dev.thiagokoster.encrypt.services.AuthService;

import dev.thiagokoster.encrypt.dtos.LoginRequest;
import dev.thiagokoster.encrypt.exceptions.AuthenticationFailedException;
import dev.thiagokoster.encrypt.models.User;
import dev.thiagokoster.encrypt.repositories.UserRepository;
import dev.thiagokoster.encrypt.services.AuthService;
import dev.thiagokoster.encrypt.services.CryptoService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class LoginTest {
    @Mock
    UserRepository userRepository;

    @Mock
    CryptoService cryptoService;

    @InjectMocks
    AuthService authService;

    @Test
    public void shouldThrow_whenUserDoesNotExist() {
        // Arrange
        String nonExistingUser = "ghost";
        LoginRequest request = new LoginRequest(nonExistingUser, "pass");
        when(userRepository.findByUsernameOrEmail(nonExistingUser, nonExistingUser))
                .thenReturn(Optional.empty());

        // Act
        AuthenticationFailedException ex = assertThrows(
                AuthenticationFailedException.class,
                () -> authService.login(request)
        );

        // Assert
        assertEquals("Invalid credentials", ex.getMessage());
    }

    @Test
    public void shouldThrow_whenInvalidPassword() {
        // Arrange
        final String username = "admin";
        final String pass = "admin";
        Optional<User> user = Optional.of(new User(username, "admin@email.com", "hash"));
        when(userRepository.findByUsernameOrEmail(username, username))
                .thenReturn(user);
        when(cryptoService.verify(eq(pass), anyString())).thenReturn(false);

        LoginRequest request = new LoginRequest(username, pass);

        // Act
        AuthenticationFailedException ex = assertThrows(
                AuthenticationFailedException.class,
                () -> authService.login(request)
        );

        // Assert
        assertEquals("Invalid credentials", ex.getMessage());
    }
}
