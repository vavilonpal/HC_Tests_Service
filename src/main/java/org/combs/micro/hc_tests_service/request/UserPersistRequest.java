package org.combs.micro.hc_tests_service.request;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.combs.micro.hc_tests_service.enums.RoleType;

@Getter
@Setter
@PasswordMatch
public class UserPersistRequest {

    @NotBlank(message = "Username is empty")
    @Size(min = 4, max = 20, message = "Length of username should be within 4 and 20 chars")
    @UserNameNotOccupy
    private String username;

    @Email(message = "Incorrect email")
    @NotBlank(message = "Email is empty")
    @EmailNotOccupy
    private String email;

    @NotBlank(message = "Password is empty")
    private String password;

    @NotBlank(message = "Confirm password is empty")
    private String passwordConfirm;

    @NotBlank(message = "Full name is empty")
    private String fullName;


    private RoleType role;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private boolean passwordsIsMatch = false;

    @JsonCreator
    public static RoleType fromString(@NotBlank(message = "Role is empty") String stringRole) {
        return stringRole == null ? RoleType.STUDENT : RoleType.valueOf(stringRole.toUpperCase());
    }
}