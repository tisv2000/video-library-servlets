package com.github.tisv2000.video_library.service;

import com.github.tisv2000.video_library.dao.UserDao;
import com.github.tisv2000.video_library.dto.CreateUserDto;
import com.github.tisv2000.video_library.dto.UserReceiveDto;
import com.github.tisv2000.video_library.exception.ValidationException;
import com.github.tisv2000.video_library.mapper.CreateUserMapper;
import com.github.tisv2000.video_library.mapper.GetUserMapper;
import com.github.tisv2000.video_library.validator.CreateUserValidator;
import lombok.NoArgsConstructor;

import java.util.Optional;

@NoArgsConstructor
public class UserService {

    private static final UserService INSTANCE = new UserService();

    private final CreateUserValidator createUserValidator = CreateUserValidator.getInstance();
    private final UserDao userDao = UserDao.getInstance();
    private final CreateUserMapper createUserMapper = CreateUserMapper.getInstance();
    private final GetUserMapper getUserMapper = GetUserMapper.getInstance();

    // Either object - закончился успешно либо провален
    public Integer create(CreateUserDto createUserDto) {
        var validationResult = createUserValidator.isValid(createUserDto);
        if (!validationResult.isValid()) {
            throw new ValidationException(validationResult.getErrors());
        }
        var userEntity = createUserMapper.mapFrom(createUserDto);
        userDao.save(userEntity);
        return userEntity.getId();
    }

    public Optional<UserReceiveDto> login(String email, String password) {
        // почему мы не проверяем на уровне сервиса на наличие ошибок?
        return userDao.findByEmailAndPassword(email, password)
                .map(getUserMapper::mapFrom);
    }

    public static UserService getInstance() {
        return INSTANCE;
    }
}
