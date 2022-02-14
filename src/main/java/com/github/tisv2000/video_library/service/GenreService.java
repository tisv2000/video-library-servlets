package com.github.tisv2000.video_library.service;

import com.github.tisv2000.video_library.dao.GenreDao;
import com.github.tisv2000.video_library.dto.GenreDto;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class GenreService {

    private static final GenreService INSTANCE = new GenreService();
    private final GenreDao genreDao = GenreDao.getInstance();

    public List<GenreDto> findAll() {
        return genreDao.findAll().stream()
                .map(genre -> new GenreDto(
                        genre.getId().toString(),
                        genre.getTitle()
                )).collect(Collectors.toList());
    }

    public static GenreService getInstance() {
        return INSTANCE;
    }
}
