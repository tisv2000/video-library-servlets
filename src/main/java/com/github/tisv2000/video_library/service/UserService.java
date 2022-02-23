package com.github.tisv2000.video_library.service;

import com.github.tisv2000.video_library.dao.UserDao;
import com.github.tisv2000.video_library.dto.LoginDto;
import com.github.tisv2000.video_library.dto.UserCreatedDto;
import com.github.tisv2000.video_library.dto.UserReceivedDto;
import com.github.tisv2000.video_library.exception.ValidationException;
import com.github.tisv2000.video_library.mapper.UserMapper;
import com.github.tisv2000.video_library.validator.CreateUserValidator;
import lombok.NoArgsConstructor;

import java.util.Optional;

@NoArgsConstructor
public class UserService {

    private static final UserService INSTANCE = new UserService();

    private final CreateUserValidator createUserValidator = CreateUserValidator.getInstance();
    private final UserDao userDao = UserDao.getInstance();
    private final UserMapper userMapper = UserMapper.getInstance();

    // Either object - закончился успешно либо провален
    public Integer create(UserCreatedDto userCreatedDto) {
        var userEntity = userMapper.mapFromUserCreatedDtoToEntity(userCreatedDto);
        userDao.save(userEntity);
        return userEntity.getId();
    }

    public Optional<UserReceivedDto> login(LoginDto loginDto) {
        return userDao.findByEmailAndPassword(loginDto.getEmail(), loginDto.getPassword())
                .map(userMapper::mapFrom);
    }

    public Optional<UserReceivedDto> findByEmail(String email) {
        return userDao.findByEmail(email)
                .map(userMapper::mapFrom);
    }

    public static UserService getInstance() {
        return INSTANCE;
    }
}
