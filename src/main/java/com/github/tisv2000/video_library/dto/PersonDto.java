package com.github.tisv2000.video_library.dto;

import lombok.Builder;
import lombok.Value;

import java.time.LocalDate;

@Value
@Builder
public class PersonDto {
    Integer id;
    String name;
    LocalDate birthday;
}
