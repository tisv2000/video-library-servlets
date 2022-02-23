package com.github.tisv2000.video_library.mapper;

import com.github.tisv2000.video_library.dto.PersonCreatedDto;
import com.github.tisv2000.video_library.dto.PersonDto;
import com.github.tisv2000.video_library.entity.Person;
import com.github.tisv2000.video_library.util.LocalDateFormatter;
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
                .build();
    }

    public Person mapToEntity(PersonCreatedDto personCreatedDto) {
        return Person.builder()
                .name(personCreatedDto.getName())
                .birthday(LocalDateFormatter.format(personCreatedDto.getBirthday()))
                .build();
    }

    public static PersonMapper getInstance() {
        return INSTANCE;
    }
}
