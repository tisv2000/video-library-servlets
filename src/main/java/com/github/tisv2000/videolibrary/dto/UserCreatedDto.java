package com.github.tisv2000.videolibrary.dto;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class UserCreatedDto {
    String id;
    String name;
    String birthday;
    String password;
    String email;
    String image;
    String role;
    String gender;
}
