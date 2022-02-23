package com.github.tisv2000.video_library.validator;

import com.github.tisv2000.video_library.util.EmailValidator;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

// TODO 3 Папе вопрос: почему не может быть с приватным конструктором?
//@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class BaseValidator {

//    private static final BaseValidator INSTANCE = new BaseValidator();

//    public static BaseValidator getInstance() {
//        return INSTANCE;
//    }

    public void validateNotNull(String field, ValidationResult validationResult, String fieldName) {
        if (field == null || field.isEmpty()) {
            validationResult.add(Error.of("missing." + fieldName.toLowerCase(), fieldName + " is missing"));
        }
    }

    public void validateEmail(String email, ValidationResult validationResult) {
        if (email != null && !email.isEmpty()) {
            if (!EmailValidator.isValid(email)) {
                validationResult.add(Error.of("wrong.email", "Email is wrong"));
            }
        }
    }

    public void validatePassword(String password, ValidationResult validationResult) {
        if(password != null && !password.isEmpty()) {
            if (password.length() < 8) {
                validationResult.add(Error.of("short.password", "Password must be at least 8 characters long"));
            }
//            TODO 2 Папе вопрос: https://stackoverflow.com/questions/5142103/regex-to-validate-password-strength
        }
    }

    public void validateYear(String year, ValidationResult validationResult) {
        if (year != null && !year.isEmpty()) {
            try {
                var yearDigit = Integer.valueOf(year);
                var currentYear = LocalDate.now().getYear();
                if (yearDigit < 1900 || yearDigit > currentYear) {
                    validationResult.add(Error.of("wrong.format", "Release year should be between 1900 and current " + currentYear));
                }
            } catch (NumberFormatException e) {
                // TODO 3 Папе вопрос: error code / message - семантика
                validationResult.add(Error.of("wrong.format", "Release year should be a digit. Example: 2005"));
            }
        }
    }


}
