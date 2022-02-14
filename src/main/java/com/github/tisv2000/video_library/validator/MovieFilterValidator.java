package com.github.tisv2000.video_library.validator;

import com.github.tisv2000.video_library.dto.MovieFilterDto;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class MovieFilterValidator implements Validator<MovieFilterDto>{

    private static final MovieFilterValidator INSTANCE = new MovieFilterValidator();

    @Override
    public ValidationResult isValid(MovieFilterDto object) {
        var validationResult = new ValidationResult();
        // TODO implement
        return validationResult;
    }

    public static MovieFilterValidator getInstance() {
        return INSTANCE;
    }
}
