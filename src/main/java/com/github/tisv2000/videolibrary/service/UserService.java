package com.github.tisv2000.videolibrary.service;

import com.github.tisv2000.videolibrary.dao.UserDao;
import com.github.tisv2000.videolibrary.dto.LoginDto;
import com.github.tisv2000.videolibrary.dto.UserCreatedDto;
import com.github.tisv2000.videolibrary.dto.UserReceivedDto;
import com.github.tisv2000.videolibrary.mapper.UserMapper;
import lombok.NoArgsConstructor;

import java.util.Optional;

@NoArgsConstructor
public class UserService {

    private static final UserService INSTANCE = new UserService();

    private final UserDao userDao = UserDao.getInstance();
    private final UserMapper userMapper = UserMapper.getInstance();

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
