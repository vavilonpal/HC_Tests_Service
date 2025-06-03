package org.combs.micro.hc_tests_service.service;


import lombok.RequiredArgsConstructor;
import org.combs.micro.hc_tests_service.entity.User;
import org.combs.micro.hc_tests_service.mapper.UserMapper;
import org.combs.micro.hc_tests_service.repository.UserRepository;
import org.combs.micro.hc_tests_service.request.UserPersistRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserRegistrationService {
    private final UserRepository userRepository;
    @Transactional
    public User registerUser(User user){
        userRepository.save(user);
    }
}
