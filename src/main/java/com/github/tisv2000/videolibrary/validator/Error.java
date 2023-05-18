package com.github.tisv2000.videolibrary.validator;

import lombok.Value;

import static com.github.tisv2000.videolibrary.util.LocaleBundleUtils.getString;

@Value
public class Error {
    String message;

    public static Error of(String message) {
        return new Error(getString(message));
    }

    public static Error of(String message, String field) {
        return new Error(getString(field, message));
    }
}
