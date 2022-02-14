package com.github.tisv2000.video_library.mapper;

import com.github.tisv2000.video_library.dto.UserInfoDto;
import com.github.tisv2000.video_library.entity.User;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class GetUserMapper implements Mapper<User, UserInfoDto>{

    private static final GetUserMapper INSTANCE = new GetUserMapper();
    @Override
    public UserInfoDto mapFrom(User object) {
        // MapStruct
        return UserInfoDto.builder()
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
