package com.github.tisv2000.video_library.validator;

import com.github.tisv2000.video_library.dto.ReviewCreatedDto;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ReviewValidator extends BaseValidator {

    private static final ReviewValidator INSTANCE = new ReviewValidator();

    public static ReviewValidator getInstance() {
        return INSTANCE;
    }

    public ValidationResult isValid(ReviewCreatedDto review) {
        var validationResult = new ValidationResult();

        validateNotNull(review.getText(), validationResult, "Review");

        validateNotNull(review.getRate(), validationResult, "Rate");

        return validationResult;
    }

}
