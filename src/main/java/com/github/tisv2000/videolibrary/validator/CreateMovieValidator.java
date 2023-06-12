package com.github.tisv2000.videolibrary.validator;

import com.github.tisv2000.videolibrary.dto.MovieCreatedDto;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CreateMovieValidator extends BaseValidator implements Validator<MovieCreatedDto> {

    private static final CreateMovieValidator INSTANCE = new CreateMovieValidator();

    @Override
    public ValidationResult isValid(MovieCreatedDto movie) {
        var validationResult = new ValidationResult();

        validateNotNull(movie.getTitle(), validationResult, "error.missing.field.movie.title");

        validateNotNull(movie.getCountry(), validationResult, "error.missing.field.movie.country");

        if (validateNotNull(movie.getYear(), validationResult, "error.missing.field.movie.year")) {
            validateYear(movie.getYear(), validationResult);
        }

        validateNotNull(movie.getGenre(), validationResult, "error.missing.field.movie.genre");

        validateNotNull(movie.getDescription(), validationResult, "error.missing.field.movie.description");

        validateNotNull(movie.getImage(), validationResult, "error.missing.field.image");

        return validationResult;
    }

    public static CreateMovieValidator getInstance() {
        return INSTANCE;
    }
}
