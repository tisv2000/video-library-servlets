package com.github.tisv2000.video_library.validator;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ReviewFilterValidator extends BaseValidator implements Validator<String> {

    private static final ReviewFilterValidator INSTANCE = new ReviewFilterValidator();

    @Override
    public ValidationResult isValid(String email) {
        var validationResult = new ValidationResult();

        validateNotNull(email, validationResult, "allReviews.email");

        return validationResult;
    }

    public static ReviewFilterValidator getInstance() {
        return INSTANCE;
    }
}
