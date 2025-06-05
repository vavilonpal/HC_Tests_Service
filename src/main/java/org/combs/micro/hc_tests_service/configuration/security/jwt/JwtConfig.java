package org.combs.micro.hc_tests_service.configuration.security.jwt;


import com.nimbusds.jose.jwk.JWKSet;
import com.nimbusds.jose.jwk.RSAKey;
import com.nimbusds.jose.jwk.source.ImmutableJWKSet;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.combs.micro.hc_tests_service.service.UserService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtEncoder;

import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.time.Duration;

@Configuration
@Setter
@Getter
@RequiredArgsConstructor
public class JwtConfig {
    @Value("${jwt.private-key}")
    private RSAPrivateKey privateKey;
    @Value("${jwt.public-key}")
    private RSAPublicKey publicKey;
    @Value("${jwt.ttl}")
    private Duration ttl;

    private final UserService userService;


    @Bean
    public JwtEncoder jwtEncoder(){
        final var jwk =  new RSAKey.Builder(publicKey)
                .privateKey(privateKey)
                .build();
        return new NimbusJwtEncoder(new ImmutableJWKSet<>(new JWKSet(jwk)));
    }
    @Bean
    public JwtDecoder jwtDecoder(){
        return NimbusJwtDecoder.withPublicKey(publicKey).build();
    }

    @Bean
    public JwtService jwtService
            (@Value("${spring.application.name}") final String appName,
             JwtDecoder jwtDecoder){
        return new JwtService(appName, ttl, jwtEncoder(), userService);
    }

}
