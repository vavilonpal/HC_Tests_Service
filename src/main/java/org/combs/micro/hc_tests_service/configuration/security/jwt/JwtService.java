package org.combs.micro.hc_tests_service.configuration.security.jwt;

import com.nimbusds.jwt.JWTClaimsSet;
import lombok.RequiredArgsConstructor;
import org.combs.micro.hc_tests_service.entity.User;
import org.combs.micro.hc_tests_service.service.UserService;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;

import java.time.Duration;
import java.time.Instant;
import java.util.List;

@RequiredArgsConstructor
public class JwtService {
    private final String issuer;

    private final Duration ttl;

    private final JwtEncoder jwtEncoder;
    private final UserService userService;

    public String generateToken(final String username){
        User user = userService.getUserByUserName(username);
        final var claimsSet = JwtClaimsSet.builder()
                .subject(username)
                .issuer(issuer)
                .claim("userId", user.getId())
                .claim("authorities", List.of(user.getRole().getName().toString()))
                .expiresAt(Instant.now().plus(ttl))
                .build();
        return jwtEncoder.encode(JwtEncoderParameters.from(claimsSet)).getTokenValue();

    }
}
