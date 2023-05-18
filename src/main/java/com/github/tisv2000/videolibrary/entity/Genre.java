package com.github.tisv2000.videolibrary.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class Genre {
    private Integer id;
    private String title;
}
