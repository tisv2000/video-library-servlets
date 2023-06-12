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

        validateNotNull(user.getName(), validationResult, "error.missing.field.name");

        validateNotNull(user.getBirthday(), validationResult, "error.missing.field.birthday");

        if (validateNotNull(user.getEmail(), validationResult, "error.missing.field.email")) {
            validateEmail(user.getEmail(), validationResult);
        }

        if (validateNotNull(user.getPassword(), validationResult, "error.missing.field.password")) {
            validatePassword(user.getPassword(), validationResult);
        }

        validateNotNull(user.getGender(), validationResult, "error.missing.field.gender");

        if (validateNotNull(user.getBirthday(), validationResult, "error.missing.field.birthday")) {
            validateYear(user.getBirthday().substring(0, 4), validationResult);
        }

        validateNotNull(user.getImage(), validationResult, "error.missing.field.image");

        return validationResult;
    }

    public static CreateUserValidator getInstance() {
        return INSTANCE;
    }
}
