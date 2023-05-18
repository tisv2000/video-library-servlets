package com.github.tisv2000.videolibrary.validator;

import com.github.tisv2000.videolibrary.dto.PersonFilterDto;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class PersonFilterValidator extends BaseValidator implements Validator<PersonFilterDto> {

    private static final PersonFilterValidator INSTANCE = new PersonFilterValidator();

    @Override
    public ValidationResult isValid(PersonFilterDto person) {
        var validationResult = new ValidationResult();

        validateNotNull(person.getName(), validationResult, "person.filter.name");

        return validationResult;
    }

    public static PersonFilterValidator getInstance() {
        return INSTANCE;
    }
}
