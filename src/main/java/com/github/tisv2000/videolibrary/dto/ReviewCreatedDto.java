package com.github.tisv2000.videolibrary.dto;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class ReviewCreatedDto {
    String userId;
    String movieId;
    String text;
    String rate;
}
