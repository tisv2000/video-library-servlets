package com.github.tisv2000.video_library.mapper;

import com.github.tisv2000.video_library.dto.GetUserDto;
import com.github.tisv2000.video_library.entity.User;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class GetUserMapper implements Mapper<User, GetUserDto>{

    private static final GetUserMapper INSTANCE = new GetUserMapper();
    @Override
    public GetUserDto mapFrom(User object) {
        // MapStruct
        return GetUserDto.builder()
                .name(object.getName())
                .birthday(object.getBirthday())
                .email(object.getEmail())
                .image(object.getImage())
                .gender(object.getGender())
                .role(object.getRole())
                .build();
    }

    public static GetUserMapper getInstance() {
        return INSTANCE;
    }
}
