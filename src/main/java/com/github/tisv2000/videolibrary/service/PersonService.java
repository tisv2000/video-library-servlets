package com.github.tisv2000.videolibrary.service;

import com.github.tisv2000.videolibrary.dao.PersonDao;
import com.github.tisv2000.videolibrary.dto.PersonCreatedDto;
import com.github.tisv2000.videolibrary.dto.PersonDto;
import com.github.tisv2000.videolibrary.dto.PersonFilterDto;
import com.github.tisv2000.videolibrary.mapper.PersonMapper;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Optional;

import static java.util.stream.Collectors.toList;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class PersonService {

    private static final PersonService INSTANCE = new PersonService();
    private static final PersonDao personDao = PersonDao.getInstance();
    private static final PersonMapper personMapper = PersonMapper.getInstance();

    public List<PersonDto> findAll() {
        return personDao.findAll().stream()
                .map(entity -> personMapper.mapToPersonDto(entity))
                .collect(toList());
    }

    public Optional<PersonDto> findById(int personId) {
        var maybePerson = personDao.findById(personId);
        return maybePerson.map(person -> personMapper.mapToPersonDto(person));
    }

    public Integer createPerson(PersonCreatedDto personCreatedDto) {
        var personEntity = personMapper.mapToEntity(personCreatedDto);
        personDao.save(personEntity);
        return personEntity.getId();
    }

    public List<PersonDto> findAllByFilter(PersonFilterDto personFilterDto) {
        return personDao.findAllByName(personFilterDto).stream()
                .map(entity -> personMapper.mapToPersonDto(entity))
                .collect(toList());
    }

    public static PersonService getInstance() {
        return INSTANCE;
    }
}
