package com.github.tisv2000.videolibrary.dto;

import com.github.tisv2000.videolibrary.entity.Genre;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class MovieReceivedDto {
    Integer id;
    String title;
    Integer year;
    String country;
    Genre genre;
    String image;
    String description;
}
