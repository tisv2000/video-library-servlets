package com.github.tisv2000.video_library.dao;

import com.github.tisv2000.video_library.entity.Genre;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Optional;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class GenreDaoTest {

    private final GenreDao underTest = GenreDao.getInstance();

    public static final Integer GENRE_ID = 6;
    public static final String GENRE_TITLE = "Drama";

    @Test
    public void findGenreByIdPositiveTest() {
        // GIVEN
        // WHEN
        Optional<Genre> maybeGenre = underTest.findById(GENRE_ID);

        // THEN
        assertTrue(maybeGenre.isPresent(), "Genre is missing!");
        assertEquals(maybeGenre.get().getTitle(), GENRE_TITLE, "Genre title is wrong!");
    }

    @Test
    public void findAllGenres() {
        // GIVEN
        // WHEN
        List<Genre> genres = underTest.findAll();

        // THEN
        assertEquals(9, genres.size(), "Amount of genre titles is not correct!");
    }
}
