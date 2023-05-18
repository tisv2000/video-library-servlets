package com.github.tisv2000.videolibrary.dto;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class MovieFilterDto {
    String title;
    String year;
    String country;
    String genre;
}
