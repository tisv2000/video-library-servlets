package com.github.tisv2000.video_library.dto;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class PersonCreatedDto {
    String name;
    String birthday;
}
