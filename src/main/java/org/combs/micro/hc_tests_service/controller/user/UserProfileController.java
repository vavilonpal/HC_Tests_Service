package org.combs.micro.hc_tests_service.controller.user;


import lombok.RequiredArgsConstructor;
import org.combs.micro.hc_tests_service.mapper.UserMapper;
import org.combs.micro.hc_tests_service.response.UserProfileResponse;
import org.combs.micro.hc_tests_service.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
public class UserProfileController {
    private final UserService userService;
    private UserMapper userMapper;

    @GetMapping("/me")
    public ResponseEntity<UserProfileResponse> getUserProfile(final Authentication authentication){
        final var user = userService.getUserByUserName(authentication.getName());

        UserProfileResponse userProfileResponse = userMapper.entityToProfileResponse(user);

        return ResponseEntity.ok(userProfileResponse);
    }



}
