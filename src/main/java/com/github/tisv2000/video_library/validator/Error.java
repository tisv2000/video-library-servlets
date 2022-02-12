package com.github.tisv2000.video_library.validator;

import lombok.Value;

// подставляет private final к каждому полю, нет setters
@Value(staticConstructor = "of")
public class Error {
    String code;
    String message;
}
