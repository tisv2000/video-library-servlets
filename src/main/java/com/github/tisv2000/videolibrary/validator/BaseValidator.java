package com.github.tisv2000.videolibrary.validator;

import com.github.tisv2000.videolibrary.util.EmailValidator;

import java.time.LocalDate;

public abstract class BaseValidator {

    public boolean validateNotNull(String field, ValidationResult validationResult, String errorCode) {
        if (field == null || field.isEmpty()) {
            validationResult.add(Error.of(errorCode));
            return false;
        }
        return true;
    }

    public boolean validateEmail(String email, ValidationResult validationResult) {
        if (email != null && !EmailValidator.isValid(email)) {
            validationResult.add(Error.of("error.wrong.email"));
            return false;
        }
        return true;
    }

    public boolean validatePassword(String password, ValidationResult validationResult) {
        if (password != null && password.length() < 8) {
            validationResult.add(Error.of("error.short.password"));
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
                    validationResult.add(Error.of("error.wrong.range.date"));
                }
            } catch (NumberFormatException e) {
                validationResult.add(Error.of("error.wrong.format.date"));
            }
        }
    }
}
