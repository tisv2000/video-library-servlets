package com.github.tisv2000.videolibrary.validator;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ReviewFilterValidator extends BaseValidator implements Validator<String> {

    private static final ReviewFilterValidator INSTANCE = new ReviewFilterValidator();

    @Override
    public ValidationResult isValid(String email) {
        var validationResult = new ValidationResult();

        validateNotNull(email, validationResult, "error.missing.field.email");

        return validationResult;
    }

    public static ReviewFilterValidator getInstance() {
        return INSTANCE;
    }
}
