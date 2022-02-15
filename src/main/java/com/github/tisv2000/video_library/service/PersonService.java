package com.github.tisv2000.video_library.service;

import com.github.tisv2000.video_library.dao.PersonDao;
import com.github.tisv2000.video_library.dto.PersonCreateDto;
import com.github.tisv2000.video_library.dto.PersonDto;
import com.github.tisv2000.video_library.dto.PersonFilterDto;
import com.github.tisv2000.video_library.mapper.PersonMapper;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.List;

import static java.util.stream.Collectors.toList;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class PersonService {

    private static final PersonService INSTANCE = new PersonService();
    private static PersonDao personDao = PersonDao.getInstance();
    private static PersonMapper personMapper = PersonMapper.getInstance();

    public static PersonService getInstance() {
        return INSTANCE;
    }

    public List<PersonDto> findAll() {
        return personDao.findAll().stream()
                .map(entity -> personMapper.mapToPersonInfoDto(entity))
                .collect(toList());
    }

    public PersonDto findById(int personId) {
        var maybePerson = personDao.findById(personId);
        return maybePerson.map(person -> personMapper.mapToPersonInfoDto(person)).orElse(null);
        // TODO ???
    }

    public Integer createPerson(PersonCreateDto personCreatedDto) {
        var personEntity = personMapper.mapToEntity(personCreatedDto);
        personDao.save(personMapper.mapToEntity(personCreatedDto));
        return personEntity.getId();
    }

    public List<PersonDto> findAllByFilter(PersonFilterDto personFilterDto) {
        return personDao.findAllByFilter(personFilterDto).stream()
                .map(entity -> personMapper.mapToPersonInfoDto(entity))
                .collect(toList());
    }
}
