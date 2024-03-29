package com.github.tisv2000.videolibrary.dto;

import com.github.tisv2000.videolibrary.entity.Gender;
import com.github.tisv2000.videolibrary.entity.Role;
import lombok.Builder;
import lombok.Value;

import java.time.LocalDate;

@Value
@Builder
public class UserReceivedDto {
    Integer id;
    String name;
    LocalDate birthday;
    String email;
    String image;
    Role role;
    Gender gender;
}
