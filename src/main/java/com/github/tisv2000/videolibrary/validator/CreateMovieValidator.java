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

        validateNotNull(movie.getTitle(), validationResult, "movie.newMovieTitle");

        validateNotNull(movie.getCountry(), validationResult, "movie.newMovieCountry");

        if (validateNotNull(movie.getYear(), validationResult, "movie.newMovieYear")) {
            validateYear(movie.getYear(), validationResult);
        }

        validateNotNull(movie.getGenre(), validationResult, "movie.newMovieGenre");

        validateNotNull(movie.getDescription(), validationResult, "movie.newMovieDescription");

        validateNotNull(movie.getImage(), validationResult, "movie.newMovieImage");

        return validationResult;
    }

    public static CreateMovieValidator getInstance() {
        return INSTANCE;
    }
}
