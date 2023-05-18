package com.github.tisv2000.videolibrary.validator;

import com.github.tisv2000.videolibrary.dto.MovieFilterDto;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class MovieFilterValidator extends BaseValidator implements Validator<MovieFilterDto>{

    private static final MovieFilterValidator INSTANCE = new MovieFilterValidator();

    @Override
    public ValidationResult isValid(MovieFilterDto movieFilterDto) {
        var validationResult = new ValidationResult();

        validateYear(movieFilterDto.getYear(), validationResult);

        return validationResult;
    }

    public static MovieFilterValidator getInstance() {
        return INSTANCE;
    }
}
