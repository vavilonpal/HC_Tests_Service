package org.combs.micro.hc_tests_service.request.user;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AuthenticationRequest {
   private String username;
   private String password;
}
