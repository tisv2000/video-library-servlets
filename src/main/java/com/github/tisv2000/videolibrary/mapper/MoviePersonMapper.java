package com.github.tisv2000.videolibrary.mapper;

import com.github.tisv2000.videolibrary.dto.MoviePersonCreatedDto;
import com.github.tisv2000.videolibrary.dto.MoviePersonReceivedDto;
import com.github.tisv2000.videolibrary.dto.PersonRoleDto;
import com.github.tisv2000.videolibrary.entity.Movie;
import com.github.tisv2000.videolibrary.entity.MoviePerson;
import com.github.tisv2000.videolibrary.entity.Person;
import com.github.tisv2000.videolibrary.entity.PersonRole;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class MoviePersonMapper {

    private static final MoviePersonMapper INSTANCE = new MoviePersonMapper();

    public MoviePerson mapMoviePersonCreateDtoToEntity(MoviePersonCreatedDto moviePersonCreatedDto) {
        return MoviePerson.builder()
                .movie(Movie
                        .builder()
                        .id(Integer.valueOf(moviePersonCreatedDto.getMovieId()))
                        .build())
                .person(Person
                        .builder()
                        .id(Integer.valueOf(moviePersonCreatedDto.getPersonId()))
                        .build())
                .role(PersonRole
                        .builder()
                        .id(Integer.valueOf(moviePersonCreatedDto.getRoleId()))
                        .build())
                .build();
    }
    public MoviePersonReceivedDto mapEntityToMoviePersonReceiveDto(MoviePerson entity) {
        return MoviePersonReceivedDto.builder()
                .personId(entity.getPerson().getId())
                .name(entity.getPerson().getName())
                .role(PersonRoleDto.builder()
                        .id(entity.getRole().getId())
                        .title(entity.getRole().getTitle())
                        .build())
                .build();
    }

    public static MoviePersonMapper getInstance() {
        return INSTANCE;
    }
}
