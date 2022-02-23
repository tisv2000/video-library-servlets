package com.github.tisv2000.video_library.validator;

@Deprecated
public interface Validator<T> {

    ValidationResult isValid(T object);
}
