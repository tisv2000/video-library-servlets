package com.github.tisv2000.videolibrary.service;

import com.github.tisv2000.videolibrary.dao.MoviePersonDao;
import com.github.tisv2000.videolibrary.dto.MoviePersonCreatedDto;
import com.github.tisv2000.videolibrary.dto.MoviePersonReceivedDto;
import com.github.tisv2000.videolibrary.mapper.MoviePersonMapper;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.List;

import static java.util.stream.Collectors.toList;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class MoviePersonService {

    private static final MoviePersonService INSTANCE = new MoviePersonService();
    private final MoviePersonDao moviePersonDao = MoviePersonDao.getInstance();
    private final MoviePersonMapper moviePersonMapper = MoviePersonMapper.getInstance();

    public Integer addMoviePerson(MoviePersonCreatedDto moviePersonCreatedDto) {
        var entity = moviePersonMapper.mapMoviePersonCreateDtoToEntity(moviePersonCreatedDto);
        moviePersonDao.save(entity);
        return entity.getId();
    }

    public List<MoviePersonReceivedDto> findAllMoviePersonsByMovieId(int movieId) {
        return moviePersonDao.findAllByMovieId(movieId).stream()
                .map(entity -> moviePersonMapper.mapEntityToMoviePersonReceiveDto(entity))
                .collect(toList());
    }

    public static MoviePersonService getInstance() {
        return INSTANCE;
    }
}
