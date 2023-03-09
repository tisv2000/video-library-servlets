package com.github.tisv2000.video_library.validator;

public interface Validator<T> {

    ValidationResult isValid(T object);
}
