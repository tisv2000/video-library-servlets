package com.github.tisv2000.videolibrary.validator;

import com.github.tisv2000.videolibrary.dto.PersonCreatedDto;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CreatePersonValidator extends BaseValidator implements Validator<PersonCreatedDto> {

    private static final CreatePersonValidator INSTANCE = new CreatePersonValidator();

    @Override
    public ValidationResult isValid(PersonCreatedDto person) {
        var validationResult = new ValidationResult();

        validateNotNull(person.getName(), validationResult, "error.missing.field.name");

        if (validateNotNull(person.getBirthday(), validationResult, "error.missing.field.birthday")) {
            validateYear(person.getBirthday().substring(0, 4), validationResult);
        }

        validateNotNull(person.getImage(), validationResult, "error.missing.field.image");

        return validationResult;
    }

    public static CreatePersonValidator getInstance() {
        return INSTANCE;
    }
}
