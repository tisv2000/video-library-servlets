package com.github.tisv2000.video_library.mapper;

import com.github.tisv2000.video_library.dto.CreateUserDto;
import com.github.tisv2000.video_library.entity.Gender;
import com.github.tisv2000.video_library.entity.Role;
import com.github.tisv2000.video_library.entity.User;
import com.github.tisv2000.video_library.util.LocalDateFormatter;

public class CreateUserMapper implements Mapper<CreateUserDto, User>{

    private static final CreateUserMapper INSTANCE = new CreateUserMapper();

    @Override
    public User mapFrom(CreateUserDto object) {
        return User.builder()
                .name(object.getName())
                .birthday(LocalDateFormatter.format(object.getBirthday()))
                .email(object.getEmail())
                .password(object.getPassword())
                .gender(Gender.valueOf(object.getGender())) // TODO как valueOf работает тут?
                .role(Role.valueOf(object.getRole()))
                .build();
    }

    public static CreateUserMapper getInstance() {
        return INSTANCE;
    }
}
