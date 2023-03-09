package com.github.tisv2000.video_library.validator;

import com.github.tisv2000.video_library.dto.ReviewCreatedDto;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ReviewValidator extends BaseValidator implements Validator<ReviewCreatedDto> {

    private static final ReviewValidator INSTANCE = new ReviewValidator();

    @Override
    public ValidationResult isValid(ReviewCreatedDto review) {
        var validationResult = new ValidationResult();

        validateNotNull(review.getText(), validationResult, "movieDetails.addReview.text");

        validateNotNull(review.getRate(), validationResult, "movieDetails.addReview.rate");

        return validationResult;
    }

    public static ReviewValidator getInstance() {
        return INSTANCE;
    }
}
