package com.github.tisv2000.video_library.exception;

import com.github.tisv2000.video_library.validator.Error;
import lombok.Getter;

import java.util.List;

public class ValidationException extends RuntimeException{

    // зачем этот лист
     @Getter
    private final List<Error> errors;

    public ValidationException(List<Error> errors) {
        this.errors = errors;
    }
}
