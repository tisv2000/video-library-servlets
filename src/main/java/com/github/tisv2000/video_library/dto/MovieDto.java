package com.github.tisv2000.video_library.dto;

import lombok.*;

@Value
@AllArgsConstructor
public class MovieDto {
    Integer id;
    String title;
    Integer year;
    String country;
    String genre;
    String image;
    String description;
}
