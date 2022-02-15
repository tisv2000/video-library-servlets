package com.github.tisv2000.video_library.dto;

import lombok.Builder;
import lombok.Value;

import java.time.LocalDate;

@Value
@Builder
public class PersonFilterDto {
    String name;
    String birthday;
}
