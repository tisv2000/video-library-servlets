package com.github.tisv2000.video_library.validator;

import com.github.tisv2000.video_library.dto.MoviePersonCreatedDto;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CreateMoviePersonValidator extends BaseValidator implements Validator<MoviePersonCreatedDto> {

    private static final CreateMoviePersonValidator INSTANCE = new CreateMoviePersonValidator();

    @Override
    public ValidationResult isValid(MoviePersonCreatedDto moviePerson) {
        var validationResult = new ValidationResult();

        validateNotNull(moviePerson.getPersonId(), validationResult, "Person");

        validateNotNull(moviePerson.getRoleId(), validationResult, "Role");

        return validationResult;
    }

    public static CreateMoviePersonValidator getInstance() {
        return INSTANCE;
    }
}
