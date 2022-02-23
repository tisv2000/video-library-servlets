package com.github.tisv2000.video_library.validator;

import com.github.tisv2000.video_library.dto.PersonCreatedDto;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CreatePersonValidator extends BaseValidator {
    private static final CreatePersonValidator INSTANCE = new CreatePersonValidator();

    public ValidationResult isValid(PersonCreatedDto person) {
        var validationResult = new ValidationResult();

        validateNotNull(person.getName(), validationResult, "Name");

        validateNotNull(person.getBirthday(), validationResult, "Birthday");

        return validationResult;
    }


    public static CreatePersonValidator getInstance() {
        return INSTANCE;
    }
}
