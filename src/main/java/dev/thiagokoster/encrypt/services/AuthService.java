package dev.thiagokoster.encrypt.services;

import dev.thiagokoster.encrypt.configurations.JwtProperties;
import dev.thiagokoster.encrypt.dtos.LoginRequest;
import dev.thiagokoster.encrypt.dtos.LoginResponse;
import dev.thiagokoster.encrypt.exceptions.AuthenticationFailedException;
import dev.thiagokoster.encrypt.models.User;
import dev.thiagokoster.encrypt.repositories.UserRepository;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.Date;
import java.util.Optional;

@Service
public class AuthService {
    private final JwtProperties jwtProperties;
    private final UserRepository userRepository;
    private final CryptoService cryptoService;

    private static final String INVALID_CREDENTIALS = "Invalid credentials";

    public AuthService(JwtProperties jwtProperties, UserRepository userRepository, CryptoService cryptoService) {
        this.jwtProperties = jwtProperties;
        this.userRepository = userRepository;
        this.cryptoService = cryptoService;
    }

    public LoginResponse login(LoginRequest request) {
        Optional<User> existingUser = userRepository.findByUsernameOrEmail(request.usernameOrEmail(), request.usernameOrEmail());

        if (existingUser.isEmpty()) {
            throw new AuthenticationFailedException(INVALID_CREDENTIALS);
        }
        User user = existingUser.get();

        if (!cryptoService.verify(request.password(), user.getHashedPassword())) {
            throw new AuthenticationFailedException(INVALID_CREDENTIALS);
        }

        Instant now = Instant.now();
        String jwt = generateToken(now, user.getId().toString());

        Instant expiry = now.plusMillis(jwtProperties.expiration());
        return new LoginResponse(Timestamp.from(expiry), jwt);
    }

    private String generateToken(Instant now, String subject) {
        Instant expiry = now.plusMillis(jwtProperties.expiration());
        SecretKey key = Keys.hmacShaKeyFor(jwtProperties.secret()
                .getBytes(StandardCharsets.UTF_8));
        return Jwts.builder()
                .subject(subject)
                .issuedAt(Date.from(now))
                .expiration(Date.from(expiry))
                .signWith(key).compact();
    }
}
