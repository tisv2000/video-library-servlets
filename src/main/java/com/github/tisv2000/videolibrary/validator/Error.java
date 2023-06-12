package com.github.tisv2000.videolibrary.validator;

import lombok.Value;

@Value
public class Error {
    String errorPropertyName;

    public static Error of(String errorPropertyName) {
        return new Error(errorPropertyName);
    }
}
