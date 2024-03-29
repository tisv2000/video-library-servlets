package com.github.tisv2000.videolibrary.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class MoviePerson {
    private Integer id;
    private Movie movie;
    private Person person;
    private PersonRole role;
}
