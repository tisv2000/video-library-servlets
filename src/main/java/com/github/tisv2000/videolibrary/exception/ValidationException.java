package com.github.tisv2000.videolibrary.exception;

import com.github.tisv2000.videolibrary.validator.Error;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class ValidationException extends RuntimeException {

    @Getter
    private final List<Error> errors;
}
