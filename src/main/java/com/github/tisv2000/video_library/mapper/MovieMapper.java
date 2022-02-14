package com.github.tisv2000.video_library.mapper;

import com.github.tisv2000.video_library.dto.MovieCreateDto;
import com.github.tisv2000.video_library.entity.Genre;
import com.github.tisv2000.video_library.entity.Movie;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class MovieMapper {
    private static final MovieMapper INSTANCE = new MovieMapper();

     public static MovieMapper getInstance() {
         return INSTANCE;
     }

     // Service -> DAO
    public Movie mapMovieCreateDtoToMovieEntity(MovieCreateDto movieCreateDto) {
         return Movie.builder()
                 .title(movieCreateDto.getTitle())
                 .year(Integer.valueOf(movieCreateDto.getYear()))
                 .country(movieCreateDto.getCountry())
                 // TODO что-то тут не так, зачем туда сюда преобразовывать?
                 .genre(new Genre(Integer.valueOf(movieCreateDto.getGenre()), ""))
                 .image(movieCreateDto.getImage())
                 .description(movieCreateDto.getDescription())
                 .build();
    }
}
