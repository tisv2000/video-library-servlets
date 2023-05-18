package com.github.tisv2000.videolibrary.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@Builder
public class Person {
    private Integer id;
    private String name;
    private LocalDate birthday;
    private String image;;
}
