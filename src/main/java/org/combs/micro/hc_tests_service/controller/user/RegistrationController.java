package org.combs.micro.hc_tests_service.controller.user;


import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.combs.micro.hc_tests_service.entity.User;
import org.combs.micro.hc_tests_service.mapper.UserMapper;
import org.combs.micro.hc_tests_service.request.user.UserPersistRequest;
import org.combs.micro.hc_tests_service.response.UserResponse;
import org.combs.micro.hc_tests_service.service.UserRegistrationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class RegistrationController {

    private final UserMapper userMapper;
    private final UserRegistrationService userRegistrationService;

    @PostMapping("/register")
    public ResponseEntity<UserResponse> registerUser(@Valid @RequestBody final UserPersistRequest request){
        final User registeredUser =  userRegistrationService.registerUser(
                userMapper.fromRegisterRequestToUser(request)
        );

        UserResponse response  = userMapper.entityToResponse(registeredUser);

        return ResponseEntity.ok(response);
    }


}
