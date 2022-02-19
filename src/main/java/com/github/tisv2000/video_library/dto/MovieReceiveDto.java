package com.github.tisv2000.video_library.dto;

import com.github.tisv2000.video_library.entity.Genre;
import lombok.*;

@Value
@Builder
public class MovieReceiveDto {
    Integer id;
    String title;
    Integer year;
    String country;
    Genre genre;
    String image;
    String description;
}
