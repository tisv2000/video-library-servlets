package com.github.tisv2000.video_library.dto;

import com.github.tisv2000.video_library.entity.PersonRole;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class MoviePersonReceiveDto {
    String name;
    // TODO можно String тут?
    String role;
}
