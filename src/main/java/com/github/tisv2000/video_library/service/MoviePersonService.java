package com.github.tisv2000.video_library.service;

import com.github.tisv2000.video_library.dao.MoviePersonDao;
import com.github.tisv2000.video_library.dto.MoviePersonCreateDto;
import com.github.tisv2000.video_library.dto.MoviePersonReceiveDto;
import com.github.tisv2000.video_library.mapper.MoviePersonMapper;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.List;

import static java.util.stream.Collectors.toList;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class MoviePersonService {

    private static final MoviePersonService INSTANCE = new MoviePersonService();
    private MoviePersonDao moviePersonDao = MoviePersonDao.getInstance();
    private MoviePersonMapper moviePersonMapper = MoviePersonMapper.getInstance();

    public static MoviePersonService getInstance() {
        return INSTANCE;
    }

    public Integer addMoviePerson(MoviePersonCreateDto moviePersonCreateDto) {
        var entity = moviePersonMapper.mapMoviePersonCreateDtoToEntity(moviePersonCreateDto);
        moviePersonDao.save(entity);
        return entity.getId();
    }

    public List<MoviePersonReceiveDto> findMoviePersonsForMovie(int movieId) {
        return moviePersonDao.findAllForMovie(movieId).stream()
                .map(entity -> moviePersonMapper.mapEntityToMoviePersonReceiveDto(entity))
                .collect(toList());
    }
}
