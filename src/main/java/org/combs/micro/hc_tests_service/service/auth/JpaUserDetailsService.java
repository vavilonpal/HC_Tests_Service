package org.combs.micro.hc_tests_service.service.auth;


import lombok.RequiredArgsConstructor;
import org.combs.micro.hc_tests_service.repository.UserRepository;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class JpaUserDetailsService implements UserDetailsService {
    private final UserRepository userRepository;

    //todo test it works or no
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findUserByUsername(username).map(user ->
                User.builder()
                        .username(user.getUsername())
                        .password(user.getPasswordHash())
                        .build()
        ).orElseThrow(() -> new UsernameNotFoundException("User with username [%s] not found".formatted(username)));
    }
}
