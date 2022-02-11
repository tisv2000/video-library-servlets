package com.github.tisv2000.video_library.dto;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class CreateUserDto {
    String id;
    String name;
    String birthday;
    String password;
    String email;
    String image;
    String role;
    String gender;
}
