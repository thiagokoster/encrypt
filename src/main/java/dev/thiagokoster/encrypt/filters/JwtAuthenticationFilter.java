package dev.thiagokoster.encrypt.filters;

import com.fasterxml.jackson.databind.ObjectMapper;
import dev.thiagokoster.encrypt.dtos.ErrorResponse;
import dev.thiagokoster.encrypt.exceptions.InvalidUserException;
import dev.thiagokoster.encrypt.models.User;
import dev.thiagokoster.encrypt.repositories.UserRepository;
import dev.thiagokoster.encrypt.services.AuthService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    private final AuthService authService;
    private final UserRepository userRepository;
    private final ObjectMapper objectMapper = new ObjectMapper();

    public JwtAuthenticationFilter(AuthService authService, UserRepository userRepository) {
        this.authService = authService;
        this.userRepository = userRepository;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain)
            throws ServletException, IOException {
        String authHeader = request.getHeader("Authorization");

        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }

        String token = authHeader.substring(7);

        try {
            Claims claims = authService.authenticate(token);
            UUID userId = UUID.fromString(claims.getSubject());

            User user = userRepository.findById(userId)
                    .orElseThrow(() -> new InvalidUserException("User not found"));

            UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                    user, null, List.of()
            );
            SecurityContextHolder.getContext().setAuthentication(authentication);

            filterChain.doFilter(request, response);
        } catch (JwtException | IllegalArgumentException | InvalidUserException e) {
            writeUnauthorized(response);
        }
    }

    private void writeUnauthorized(HttpServletResponse response) throws IOException {
        ErrorResponse errorResponse = new ErrorResponse(new String[] {"Invalid or expired token"});
        String json = objectMapper.writeValueAsString(errorResponse);

        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.setContentType("application/json");
        response.getWriter().write(json);
    }
}
