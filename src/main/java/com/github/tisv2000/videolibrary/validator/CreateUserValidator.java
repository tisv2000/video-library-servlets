package com.github.tisv2000.videolibrary.validator;

import com.github.tisv2000.videolibrary.dto.UserCreatedDto;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CreateUserValidator extends BaseValidator implements Validator<UserCreatedDto> {

    private static final CreateUserValidator INSTANCE = new CreateUserValidator();

    @Override
    public ValidationResult isValid(UserCreatedDto user) {
        var validationResult = new ValidationResult();

        validateNotNull(user.getName(), validationResult, "registration.name");

        validateNotNull(user.getBirthday(), validationResult, "registration.birthday");

        if (validateNotNull(user.getEmail(), validationResult, "registration.email")) {
            validateEmail(user.getEmail(), validationResult);
        }

        if (validateNotNull(user.getGender(), validationResult, "registration.password")) {
            validatePassword(user.getPassword(), validationResult);
        }

        validateNotNull(user.getGender(), validationResult, "registration.gender");

        validateNotNull(user.getImage(), validationResult, "registration.image");

        return validationResult;
    }

    public static CreateUserValidator getInstance() {
        return INSTANCE;
    }
}
