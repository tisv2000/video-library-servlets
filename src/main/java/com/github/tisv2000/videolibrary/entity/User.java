package com.github.tisv2000.videolibrary.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@Builder
public class User {
    private Integer id;
    private String name;
    private LocalDate birthday;
    private String password;
    private String email;
    private String image;
    private Role role;
    private Gender gender;
}
