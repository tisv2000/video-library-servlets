package com.github.tisv2000.video_library.mapper;

import com.github.tisv2000.video_library.dto.MovieCreatedDto;
import com.github.tisv2000.video_library.dto.MovieReceivedDto;
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
    public Movie mapMovieCreateDtoToMovieEntity(MovieCreatedDto movieCreatedDto) {
        return Movie.builder()
                .title(movieCreatedDto.getTitle())
                .year(Integer.valueOf(movieCreatedDto.getYear()))
                .country(movieCreatedDto.getCountry())
                // TODO GenreDao - findById
                .genre(new Genre(Integer.valueOf(movieCreatedDto.getGenre()), ""))
                .image(movieCreatedDto.getImage())
                .description(movieCreatedDto.getDescription())
                .build();
    }

    public MovieReceivedDto mapMovieEntityToMovieReceiveDto(Movie movie) {
        return MovieReceivedDto.builder()
                .id(movie.getId())
                .title(movie.getTitle())
                .year(movie.getYear())
                .country(movie.getCountry())
                .genre(movie.getGenre())
                .image(movie.getImage())
                .description(movie.getDescription())
                .build();
    }

}
