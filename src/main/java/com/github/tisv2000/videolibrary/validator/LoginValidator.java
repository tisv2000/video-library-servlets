package com.github.tisv2000.videolibrary.validator;

import com.github.tisv2000.videolibrary.dto.LoginDto;
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

        validateNotNull(login.getEmail(), validationResult, "error.missing.field.email");

        validateNotNull(login.getPassword(), validationResult, "error.missing.field.password");

        return validationResult;
    }
}
