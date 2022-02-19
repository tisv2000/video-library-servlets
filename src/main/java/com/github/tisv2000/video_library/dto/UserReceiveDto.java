package com.github.tisv2000.video_library.dto;

import com.github.tisv2000.video_library.entity.Gender;
import com.github.tisv2000.video_library.entity.Role;
import lombok.Builder;
import lombok.Value;

import java.time.LocalDate;

@Value
@Builder
public class UserReceiveDto {
    Integer id;
    String name;
    LocalDate birthday;
    String email;
    String image;
    Role role;
    Gender gender;
}
