package com.github.tisv2000.video_library.validator;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class PersonFilterValidator extends BaseValidator {

    private static final PersonFilterValidator INSTANCE = new PersonFilterValidator();

    public ValidationResult isValid(com.github.tisv2000.video_library.dto.PersonFilterDto person) {
        var validationResult = new ValidationResult();

        validateNotNull(person.getName(), validationResult, "Name");

        return validationResult;
    }

    public static PersonFilterValidator getInstance() {
        return INSTANCE;
    }

}
