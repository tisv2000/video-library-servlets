package com.github.tisv2000.videolibrary.mapper;

import com.github.tisv2000.videolibrary.dto.UserCreatedDto;
import com.github.tisv2000.videolibrary.dto.UserReceivedDto;
import com.github.tisv2000.videolibrary.entity.Gender;
import com.github.tisv2000.videolibrary.entity.Role;
import com.github.tisv2000.videolibrary.entity.User;
import com.github.tisv2000.videolibrary.util.LocalDateFormatter;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class UserMapper {

    private static final UserMapper INSTANCE = new UserMapper();

    public UserReceivedDto mapFrom(User object) {
        return UserReceivedDto.builder()
                .id(object.getId())
                .name(object.getName())
                .birthday(object.getBirthday())
                .email(object.getEmail())
                .image(object.getImage())
                .gender(object.getGender())
                .role(object.getRole())
                .build();
    }

    public User mapFromUserCreatedDtoToEntity(UserCreatedDto object) {
        return User.builder()
                .name(object.getName())
                .birthday(LocalDateFormatter.format(object.getBirthday()))
                .email(object.getEmail())
                .image(object.getImage())
                .password(object.getPassword())
                .gender(Gender.valueOf(object.getGender()))
                .role(Role.valueOf(object.getRole()))
                .build();
    }

    public static UserMapper getInstance() {
        return INSTANCE;
    }
}
