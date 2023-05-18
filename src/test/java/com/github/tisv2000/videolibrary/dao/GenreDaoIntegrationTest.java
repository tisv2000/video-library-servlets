package com.github.tisv2000.videolibrary.dao;

import com.github.tisv2000.videolibrary.entity.Genre;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Optional;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class GenreDaoIntegrationTest {

    private final GenreDao genreDao = GenreDao.getInstance();

    public static final Integer GENRE_ID = 6;
    public static final String GENRE_TITLE = "Drama";

    @Test
    public void findGenreByIdPositiveTest() {
        // GIVEN
        // WHEN
        Optional<Genre> maybeGenre = genreDao.findById(GENRE_ID);

        // THEN
        assertTrue(maybeGenre.isPresent(), "Genre is missing!");
        assertEquals(maybeGenre.get().getTitle(), GENRE_TITLE, "Genre title is wrong!");
    }

    @Test
    public void findAllGenres() {
        // GIVEN
        // WHEN
        List<Genre> genres = genreDao.findAll();

        // THEN
        assertEquals(9, genres.size(), "Amount of genre titles is not correct!");
    }
}
