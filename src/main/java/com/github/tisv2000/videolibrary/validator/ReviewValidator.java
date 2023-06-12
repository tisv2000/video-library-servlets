package com.github.tisv2000.videolibrary.validator;

import com.github.tisv2000.videolibrary.dto.ReviewCreatedDto;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ReviewValidator extends BaseValidator implements Validator<ReviewCreatedDto> {

    private static final ReviewValidator INSTANCE = new ReviewValidator();

    @Override
    public ValidationResult isValid(ReviewCreatedDto review) {
        var validationResult = new ValidationResult();

        validateNotNull(review.getText(), validationResult, "error.missing.field.review.text");

        validateNotNull(review.getRate(), validationResult, "error.missing.field.review.rate");

        return validationResult;
    }

    public static ReviewValidator getInstance() {
        return INSTANCE;
    }
}
