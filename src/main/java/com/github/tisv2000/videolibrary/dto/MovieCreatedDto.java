package com.github.tisv2000.videolibrary.dto;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class MovieCreatedDto {
    String title;
    String year;
    String country;
    String image;
    String genre;
    String description;
}
