package com.github.tisv2000.video_library.mapper;

import com.github.tisv2000.video_library.dto.MoviePersonCreatedDto;
import com.github.tisv2000.video_library.dto.MoviePersonReceivedDto;
import com.github.tisv2000.video_library.dto.PersonRoleDto;
import com.github.tisv2000.video_library.entity.Movie;
import com.github.tisv2000.video_library.entity.MoviePerson;
import com.github.tisv2000.video_library.entity.Person;
import com.github.tisv2000.video_library.entity.PersonRole;
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
