package com.github.tisv2000.video_library.dto;

import lombok.*;

@Value
@AllArgsConstructor
public class MovieDto {
    private Integer id;
    private String title;
    private Integer year;
    private String country;
    private String genre;
}
