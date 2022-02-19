package com.github.tisv2000.video_library.mapper;

import com.github.tisv2000.video_library.dto.MoviePersonCreateDto;
import com.github.tisv2000.video_library.dto.MoviePersonReceiveDto;
import com.github.tisv2000.video_library.entity.Movie;
import com.github.tisv2000.video_library.entity.MoviePerson;
import com.github.tisv2000.video_library.entity.Person;
import com.github.tisv2000.video_library.entity.PersonRole;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class MoviePersonMapper {

    private static final MoviePersonMapper INSTANCE = new MoviePersonMapper();

    public static MoviePersonMapper getInstance() {
        return INSTANCE;
    }

    public MoviePerson mapMoviePersonCreateDtoToEntity(MoviePersonCreateDto moviePersonCreateDto) {
        return MoviePerson.builder()
                .movie(Movie
                        .builder()
                        .id(Integer.valueOf(moviePersonCreateDto.getMovieId()))
                        .build())
                .person(Person
                        .builder()
                        .id(Integer.valueOf(moviePersonCreateDto.getPersonId()))
                        .build())
                .role(PersonRole
                        .builder()
                        .id(Integer.valueOf(moviePersonCreateDto.getRoleId()))
                        .build())
                .build();
    }

    public MoviePersonReceiveDto mapEntityToMoviePersonReceiveDto(MoviePerson entity) {
        return MoviePersonReceiveDto.builder()
                .name(entity.getPerson().getName())
                .role(entity.getRole().getTitle())
                .build();
    }
}
