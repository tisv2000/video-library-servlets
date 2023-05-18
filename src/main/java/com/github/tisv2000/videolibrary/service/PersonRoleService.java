package com.github.tisv2000.videolibrary.service;

import com.github.tisv2000.videolibrary.dao.PersonRoleDao;
import com.github.tisv2000.videolibrary.dto.PersonRoleReceivedDao;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.List;

import static java.util.stream.Collectors.toList;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class PersonRoleService {

    private static final PersonRoleService INSTANCE = new PersonRoleService();
    private final PersonRoleDao personRoleDao = PersonRoleDao.getInstance();

    public List<PersonRoleReceivedDao> findAll() {
        return personRoleDao.findAll().stream()
                .map(entity -> PersonRoleReceivedDao
                        .builder()
                        .id(entity.getId())
                        .title(entity.getTitle())
                        .build()).collect(toList());
    }

    public static PersonRoleService getInstance() {
        return INSTANCE;
    }
}
