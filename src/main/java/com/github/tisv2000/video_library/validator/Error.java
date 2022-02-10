package com.github.tisv2000.video_library.validator;

import lombok.Value;

@Value(staticConstructor = "of") // TODO immutable ??? создается статическим методом
public class Error {
    String code; // почему private не нужен?
    String message;
}
