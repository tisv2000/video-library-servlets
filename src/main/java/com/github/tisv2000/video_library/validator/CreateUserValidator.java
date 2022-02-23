package com.github.tisv2000.video_library.validator;

import com.github.tisv2000.video_library.dto.UserCreatedDto;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CreateUserValidator extends BaseValidator implements Validator<UserCreatedDto> {

    private static final CreateUserValidator INSTANCE = new CreateUserValidator();

    @Override
    public ValidationResult isValid(UserCreatedDto user) {
        var validationResult = new ValidationResult();

        validateNotNull(user.getName(), validationResult, "Name");

        validateNotNull(user.getBirthday(), validationResult, "Birthday");

        if (validateNotNull(user.getEmail(), validationResult, "Email")) {
            validateEmail(user.getEmail(), validationResult);
        }

        if (validateNotNull(user.getGender(), validationResult, "Password")) {
            validatePassword(user.getPassword(), validationResult);
        }

        validateNotNull(user.getGender(), validationResult, "Gender");

        validateNotNull(user.getImage(), validationResult, "Image");

        return validationResult;
    }

    public static CreateUserValidator getInstance() {
        return INSTANCE;
    }
}
