package com.github.tisv2000.video_library.validator;

import com.github.tisv2000.video_library.dto.PersonCreateDto;
import com.github.tisv2000.video_library.dto.PersonFilterDto;
import com.github.tisv2000.video_library.util.LocalDateFormatter;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.Objects;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CreatePersonValidator {
    private static final CreatePersonValidator INSTANCE = new CreatePersonValidator();
    private static final int MAX_NAME_LENGTH = 256;

    public static CreatePersonValidator getInstance() {
        return INSTANCE;
    }

    public ValidationResult isValid(PersonCreateDto object) {
        var validationResult = new ValidationResult();
        if (object.getName() == null || object.getName().isEmpty()) {
            validationResult.add(Error.of("missing.name", "Name must not be empty"));
        }

        // TODO никогда не доходит сюда - если слишком длинная строка, то уже вернется null из jsp...
        else {
            validateNameLength(object.getName(), validationResult);
        }
        validateBirthday(object.getBirthday(), validationResult);

        return validationResult;
    }

    public ValidationResult isValid(PersonFilterDto object) {
        var validationResult = new ValidationResult();
        // isEmpty()
        if (!object.getName().isEmpty()) {
            if (object.getName().length() >= MAX_NAME_LENGTH) {
                validationResult.add(Error.of("maximum.length.name", "Name cannot be longer than 256 characters"));
            }
        }

        return validationResult;
    }

    private void validateNameLength(String name, ValidationResult validationResult) {
        if (name.length() >= MAX_NAME_LENGTH) {
            validationResult.add(Error.of("maximum.length.name", "Name cannot be longer than 256 characters"));
        }
    }


        private void validateBirthday(String birthday, ValidationResult validationResult) {
        if (birthday.isEmpty()) {
            validationResult.add(Error.of("missing.date", "Birthday must not be empty"));
        }

        // никогда не выполнится
        else if (!LocalDateFormatter.isValid(birthday)) {
            validationResult.add(Error.of("invalid.date", "Invalid birthday. Should be of format yyyy-MM-dd"));
        }
    }
}
