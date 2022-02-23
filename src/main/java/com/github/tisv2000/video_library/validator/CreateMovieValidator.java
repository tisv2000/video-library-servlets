package com.github.tisv2000.video_library.validator;

import com.github.tisv2000.video_library.dto.MovieCreatedDto;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CreateMovieValidator extends BaseValidator implements Validator<MovieCreatedDto> {

    private static final CreateMovieValidator INSTANCE = new CreateMovieValidator();

    @Override
    public ValidationResult isValid(MovieCreatedDto movie) {
        var validationResult = new ValidationResult();

        validateNotNull(movie.getTitle(), validationResult, "Title");

        validateNotNull(movie.getCountry(), validationResult, "Country");

        if (validateNotNull(movie.getYear(), validationResult, "Year")) {
            validateYear(movie.getYear(), validationResult);
        }

        validateNotNull(movie.getGenre(), validationResult, "Genre");

        validateNotNull(movie.getDescription(), validationResult, "Description");

        validateNotNull(movie.getImage(), validationResult, "Image");

        return validationResult;
    }

    public static CreateMovieValidator getInstance() {
        return INSTANCE;
    }
}
