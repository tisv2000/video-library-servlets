package com.github.tisv2000.videolibrary.mapper;

import com.github.tisv2000.videolibrary.dto.PersonCreatedDto;
import com.github.tisv2000.videolibrary.dto.PersonDto;
import com.github.tisv2000.videolibrary.entity.Person;
import com.github.tisv2000.videolibrary.util.LocalDateFormatter;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class PersonMapper {

    private static final PersonMapper INSTANCE = new PersonMapper();

    public PersonDto mapToPersonDto(Person entity) {
        return PersonDto.builder()
                .id(entity.getId())
                .name(entity.getName())
                .birthday(entity.getBirthday())
                .image(entity.getImage())
                .build();
    }

    public Person mapToEntity(PersonCreatedDto personCreatedDto) {
        return Person.builder()
                .name(personCreatedDto.getName())
                .birthday(LocalDateFormatter.format(personCreatedDto.getBirthday()))
                .image(personCreatedDto.getImage())
                .build();
    }

    public static PersonMapper getInstance() {
        return INSTANCE;
    }
}
