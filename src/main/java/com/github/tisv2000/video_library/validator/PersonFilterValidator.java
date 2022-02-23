package com.github.tisv2000.video_library.validator;

import com.github.tisv2000.video_library.dto.PersonFilterDto;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class PersonFilterValidator extends BaseValidator implements Validator<PersonFilterDto> {

    private static final PersonFilterValidator INSTANCE = new PersonFilterValidator();

    @Override
    public ValidationResult isValid(PersonFilterDto person) {
        var validationResult = new ValidationResult();

        validateNotNull(person.getName(), validationResult, "Name");

        return validationResult;
    }

    public static PersonFilterValidator getInstance() {
        return INSTANCE;
    }
}
