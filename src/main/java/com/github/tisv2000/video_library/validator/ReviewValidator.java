package com.github.tisv2000.video_library.validator;

import com.github.tisv2000.video_library.dto.ReviewCreateDto;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ReviewValidator {

    private static final ReviewValidator INSTANCE = new ReviewValidator();

    public static ReviewValidator getInstance() {
        return INSTANCE;
    }

    public ValidationResult isValid(ReviewCreateDto reviewCreateDto) {
        var validationResult = new ValidationResult();
        if (reviewCreateDto.getText().isEmpty()) {
            validationResult.add(Error.of("text.empty", "Text must not be empty"));
        }
//        try {
            int rate = Integer.parseInt(reviewCreateDto.getRate());
        if (rate < 1 || rate > 11) {
            validationResult.add(Error.of("rate.wrong.range", "Rate should be between 1 and 10"));
        }
//        }
        return validationResult;
    }

}
