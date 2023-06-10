package com.github.tisv2000.videolibrary.mapper;

import com.github.tisv2000.videolibrary.dao.GenreDao;
import com.github.tisv2000.videolibrary.dto.MovieCreatedDto;
import com.github.tisv2000.videolibrary.dto.MovieReceivedDto;
import com.github.tisv2000.videolibrary.entity.*;
import com.github.tisv2000.videolibrary.exception.*;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class MovieMapper {

    private static final MovieMapper INSTANCE = new MovieMapper();
    private final GenreDao genreDao = GenreDao.getInstance();

    public Movie mapMovieCreateDtoToMovieEntity(MovieCreatedDto movieCreatedDto) {
        int genreId = Integer.parseInt(movieCreatedDto.getGenre());
        return Movie.builder()
                .title(movieCreatedDto.getTitle())
                .year(Integer.valueOf(movieCreatedDto.getYear()))
                .country(movieCreatedDto.getCountry())
                .genre(genreDao.findById(genreId).orElseThrow(() ->
                        new GenreNotFoundException("Genre with id " + genreId))
                )
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

    public static MovieMapper getInstance() {
        return INSTANCE;
    }
}
