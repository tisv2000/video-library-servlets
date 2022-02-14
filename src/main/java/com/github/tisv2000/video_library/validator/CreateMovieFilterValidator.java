package com.github.tisv2000.video_library.validator;

import com.github.tisv2000.video_library.dto.MovieCreateDto;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CreateMovieFilterValidator implements Validator<MovieCreateDto> {
    private static final CreateMovieFilterValidator INSTANCE = new CreateMovieFilterValidator();

    @Override
    public ValidationResult isValid(MovieCreateDto object) {
        var validationResult = new ValidationResult();
        // TODO implement
        return validationResult;
    }

    public static CreateMovieFilterValidator getInstance() {
        return INSTANCE;
    }
}

