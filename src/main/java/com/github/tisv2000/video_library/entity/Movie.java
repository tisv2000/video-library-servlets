package com.github.tisv2000.video_library.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class Movie {
    private Integer id;
    private String title;
    private Integer year;
    private String country;
    private Genre genre;
}
