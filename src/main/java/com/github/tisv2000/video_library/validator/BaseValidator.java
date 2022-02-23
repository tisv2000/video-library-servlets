package com.github.tisv2000.video_library.validator;

import com.github.tisv2000.video_library.util.EmailValidator;

import java.time.LocalDate;

public class BaseValidator {

    public boolean validateNotNull(String field, ValidationResult validationResult, String fieldName) {
        if (field == null || field.isEmpty()) {
            validationResult.add(Error.of("missing." + fieldName.toLowerCase(), fieldName + " is missing"));
            return false;
        }
        return true;
    }

    public boolean validateEmail(String email, ValidationResult validationResult) {
        if (email != null && !EmailValidator.isValid(email)) {
            validationResult.add(Error.of("wrong.email", "Email is wrong"));
            return false;
        }
        return true;
    }

    public boolean validatePassword(String password, ValidationResult validationResult) {
        if (password != null && password.length() < 8) {
            validationResult.add(Error.of("short.password", "Password must be at least 8 characters long"));
            return false;
        }
        return true;
    }

    public void validateYear(String year, ValidationResult validationResult) {
        if (year != null && !year.isEmpty()) {
            try {
                int yearDigit = Integer.parseInt(year);
                var currentYear = LocalDate.now().getYear();
                if (yearDigit < 1900 || yearDigit > currentYear) {
                    validationResult.add(Error.of("wrong.format", "Release year should be between 1900 and current " + currentYear));
                }
            } catch (NumberFormatException e) {
                validationResult.add(Error.of("wrong.year", "Release year should be a digit. Example: 2005"));
            }
        }
    }
}
