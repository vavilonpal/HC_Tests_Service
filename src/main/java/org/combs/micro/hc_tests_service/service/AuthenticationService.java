package org.combs.micro.hc_tests_service.service;


import lombok.RequiredArgsConstructor;
import org.combs.micro.hc_tests_service.configuration.security.JwtService;
import org.combs.micro.hc_tests_service.repository.UserRepository;
import org.combs.micro.hc_tests_service.request.user.AuthenticationRequest;
import org.combs.micro.hc_tests_service.response.AuthenticationResponse;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    public AuthenticationResponse authenticate(final AuthenticationRequest request) {
        // Спринг создает непроверренный токен на основе username and password
        final var  authToken = UsernamePasswordAuthenticationToken
                .unauthenticated(request.getUsername(), request.getPassword());

        // Спринг проверяет логин и пароль в базе данных, сравнивает пароли с учетом хэша
        final var authentication = authenticationManager.authenticate(authToken);

        // Генерируем токен
        final var token = jwtService.generateToken(request.getUsername());

        return new AuthenticationResponse(token);
    }
}
