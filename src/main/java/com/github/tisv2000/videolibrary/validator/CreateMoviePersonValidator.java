package com.github.tisv2000.videolibrary.validator;

import com.github.tisv2000.videolibrary.dto.MoviePersonCreatedDto;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CreateMoviePersonValidator extends BaseValidator implements Validator<MoviePersonCreatedDto> {

    private static final CreateMoviePersonValidator INSTANCE = new CreateMoviePersonValidator();

    @Override
    public ValidationResult isValid(MoviePersonCreatedDto moviePerson) {
        var validationResult = new ValidationResult();

        validateNotNull(moviePerson.getPersonId(), validationResult, "error.missing.field.name");

        validateNotNull(moviePerson.getRoleId(), validationResult, "error.missing.field.role");

        return validationResult;
    }

    public static CreateMoviePersonValidator getInstance() {
        return INSTANCE;
    }
}
