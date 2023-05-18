package com.github.tisv2000.videolibrary.dto;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class MoviePersonCreatedDto {
    String movieId;
    String personId;
    String roleId;
}
