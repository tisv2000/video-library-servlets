package com.github.tisv2000.video_library.validator;

import com.github.tisv2000.video_library.dto.LoginDto;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class LoginValidator extends BaseValidator implements Validator<LoginDto> {

    public static final LoginValidator INSTANCE = new LoginValidator();

    public static LoginValidator getInstance() {
        return INSTANCE;
    }

    @Override
    public ValidationResult isValid(LoginDto login) {
        var validationResult = new ValidationResult();

        validateNotNull(login.getEmail(), validationResult, "Email");

        validateNotNull(login.getPassword(), validationResult, "Password");

        return validationResult;
    }
}
