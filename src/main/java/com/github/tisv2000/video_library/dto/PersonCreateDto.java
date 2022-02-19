package com.github.tisv2000.video_library.dto;

import lombok.Builder;
import lombok.Value;

@Value // immutable - lombok подставит private final
@Builder
public class PersonCreateDto {
    String name;
    String birthday;
}
