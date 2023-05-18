package com.github.tisv2000.videolibrary.service;

import com.github.tisv2000.videolibrary.dao.GenreDao;
import com.github.tisv2000.videolibrary.dto.GenreDto;
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
                .map(genre -> GenreDto.builder()
                        .id(genre.getId().toString())
                        .title(genre.getTitle())
                        .build()
                ).collect(Collectors.toList());
    }

    public static GenreService getInstance() {
        return INSTANCE;
    }
}
