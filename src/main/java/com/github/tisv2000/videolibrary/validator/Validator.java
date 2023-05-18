package com.github.tisv2000.videolibrary.validator;

public interface Validator<T> {

    ValidationResult isValid(T object);
}
