package com.github.tisv2000.video_library.validator;

import com.github.tisv2000.video_library.dto.CreateUserDto;
import com.github.tisv2000.video_library.entity.Gender;
import com.github.tisv2000.video_library.util.LocalDateFormatter;

public class CreateUserValidator implements Validator<CreateUserDto> {

    // зачем его делать singleton? - могут быть зависимости на другие классы, например на dao (проверить email на уникальность)
    private static final CreateUserValidator INSTANCE = new CreateUserValidator();

    // TODO дописать
    @Override
    public ValidationResult isValid(CreateUserDto object) {
        var validationResult = new ValidationResult();
        if (!LocalDateFormatter.isValid(object.getBirthday())) {
            validationResult.add(Error.of("invalid.birthday", "Birthday is invalid"));
        }
        if(Gender.valueOf(object.getGender()) == null) {
            validationResult.add(Error.of("invalid.gender", "Gender is invalid"));
        }
        return validationResult;
    }

    public static CreateUserValidator getInstance() {
        return INSTANCE;
    }
}
