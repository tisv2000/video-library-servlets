package com.github.tisv2000.video_library.service;

import com.github.tisv2000.video_library.dao.UserDao;
import com.github.tisv2000.video_library.dto.CreateUserDto;
import com.github.tisv2000.video_library.entity.User;
import com.github.tisv2000.video_library.exception.ValidationException;
import com.github.tisv2000.video_library.mapper.CreateUserMapper;
import com.github.tisv2000.video_library.validator.CreateUserValidator;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class UserService {

    private static final UserService INSTANCE = new UserService();

    private final CreateUserValidator createUserValidator = CreateUserValidator.getInstance();
    private final UserDao userDao = UserDao.getInstance();
    // почему нет ошибки, это же singleton?
//    private final CreateUserMapper createUserMapper = new CreateUserMapper();
    private final CreateUserMapper createUserMapper = CreateUserMapper.getInstance();

    // Either object - закончился успешно либо провален
    public Integer create(CreateUserDto createUserDto) {
        // validation - service level, can check smth in db
        // map dto -> entity
        // userDao.save in db
        // return ?
        var validationResult = createUserValidator.isValid(createUserDto);
        if (!validationResult.isValid()) {
            throw new ValidationException(validationResult.getErrors());
        }
        var userEntity = createUserMapper.mapFrom(createUserDto);
        userDao.save(userEntity);
        return userEntity.getId();

    }

    public static UserService getInstance() {
        return INSTANCE;
    }
}
