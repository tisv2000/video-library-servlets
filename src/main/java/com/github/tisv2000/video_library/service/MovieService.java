package com.github.tisv2000.video_library.service;


import com.github.tisv2000.video_library.dao.MovieDao;
import com.github.tisv2000.video_library.dto.MovieCreatedDto;
import com.github.tisv2000.video_library.dto.MovieReceivedDto;
import com.github.tisv2000.video_library.dto.MovieFilterDto;
import com.github.tisv2000.video_library.mapper.MovieMapper;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Optional;

import static java.util.stream.Collectors.toList;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class MovieService {

    private static final MovieService INSTANCE = new MovieService();
    private final MovieDao movieDao = MovieDao.getInstance();
    private final MovieMapper movieMapper = MovieMapper.getInstance();

    public List<MovieReceivedDto> findAll() {

        return movieDao.findAll().stream()
                .map(movieMapper::mapMovieEntityToMovieReceiveDto)
                .collect(toList());
    }

    public Optional<MovieReceivedDto> findById(Integer id) {
        var maybeMovie = movieDao.findById(id);
        return maybeMovie.map(movieMapper::mapMovieEntityToMovieReceiveDto);
    }

    public List<MovieReceivedDto> findAllByFilters(MovieFilterDto movieFilterDto) {
        return movieDao.findAllByFilters(movieFilterDto).stream().map(
                        movieMapper::mapMovieEntityToMovieReceiveDto)
                .collect(toList());
    }

    public Integer createMovie(MovieCreatedDto movieCreatedDto) {
        var movie = movieMapper.mapMovieCreateDtoToMovieEntity(movieCreatedDto);
        movieDao.save(movie);
        return movie.getId();
    }

    public static MovieService getInstance() {
        return INSTANCE;
    }
}
