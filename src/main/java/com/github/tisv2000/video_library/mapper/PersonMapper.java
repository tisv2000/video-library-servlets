package com.github.tisv2000.video_library.mapper;

import com.github.tisv2000.video_library.dto.CreateUserDto;
import com.github.tisv2000.video_library.dto.PersonCreateDto;
import com.github.tisv2000.video_library.dto.PersonDto;
import com.github.tisv2000.video_library.dto.PersonFilterDto;
import com.github.tisv2000.video_library.entity.Person;
import com.github.tisv2000.video_library.util.LocalDateFormatter;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class PersonMapper {

    private static final PersonMapper INSTANCE = new PersonMapper();

    public static PersonMapper getInstance() {
        return INSTANCE;
    }

    // dao -> service
    public PersonDto mapToPersonInfoDto(Person entity) {
        return PersonDto.builder()
                .id(entity.getId())
                .name(entity.getName())
                .birthday(entity.getBirthday())
                .build();
    }

    // service - dao
    public Person mapToEntity(PersonCreateDto personCreateDto) {
        return Person.builder()
                .name(personCreateDto.getName())
                .birthday(LocalDateFormatter.format(personCreateDto.getBirthday()))
                .build();
    }
}
