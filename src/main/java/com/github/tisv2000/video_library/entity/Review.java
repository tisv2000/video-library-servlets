package com.github.tisv2000.video_library.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class Review {
    private Integer id;
    private User user;
    private Movie movie;
    private String text;
    private Integer rate;
}
