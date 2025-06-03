package org.combs.micro.hc_tests_service.controller.user;


import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.combs.micro.hc_tests_service.request.user.AuthenticationRequest;
import org.combs.micro.hc_tests_service.response.AuthenticationResponse;
import org.combs.micro.hc_tests_service.response.UserResponse;
import org.combs.micro.hc_tests_service.service.AuthenticationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthenticationService authenticationService;


    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> authenticate(
            @Valid @RequestBody AuthenticationRequest request
    ){
        AuthenticationResponse response = authenticationService.authenticate(request);

        return ResponseEntity.ok(response);
    }
}
