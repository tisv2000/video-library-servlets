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

    public static CreatePersonValidator getInstance() {
        return INSTANCE;
    }

    public ValidationResult isValid(PersonCreateDto object) {
        var validationResult = new ValidationResult();
        // TODO difference??
//        !object.getName().isEmpty();
//        object.getName() != null
//        object.getName() != ""
        if (object.getName() == null) {
            validationResult.add(Error.of("missing.name", "Name must not be empty"));
        }
        validateBirthday(object.getBirthday(), validationResult);

        return validationResult;
    }

    public ValidationResult isValid(PersonFilterDto object) {
        var validationResult = new ValidationResult();
        if (!Objects.equals(object.getBirthday(), "")) {
            validateBirthday(object.getBirthday(), validationResult);
        }
        return validationResult;
    }

    private void validateBirthday(String birthday, ValidationResult validationResult) {
        if (!LocalDateFormatter.isValid(birthday)) {
            validationResult.add(Error.of("invalid.date", "Invalid birthday. Should be of format yyyy-MM-dd"));
        }
    }
}
