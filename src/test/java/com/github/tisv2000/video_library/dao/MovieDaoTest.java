package com.github.tisv2000.video_library.dao;

import com.github.tisv2000.video_library.dao.MovieDao;
import com.github.tisv2000.video_library.entity.Genre;
import com.github.tisv2000.video_library.entity.Movie;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Optional;

import static org.testng.Assert.*;

public class MovieDaoTest {

    private final MovieDao movieDAO = MovieDao.getInstance();

    public static final Integer MOVIE_ID = 3;
    public static final String MOVIE_TITLE = "The Vampire Diaries";
    public static final Integer YEAR = 2009;
    public static final String COUNTRY = "the USA";
    public static final Integer GENRE = 5;

    public static final Movie NEW_MOVIE = new Movie(
            0,
            "Gladiator",
            2000,
            "the USA",
            new Genre(
                    3,
                    "Action"
            ),
            "",
            ""
    );

    @Test
    public void findMovieByIdPositiveTest() {
        Optional<Movie> maybeMovie = movieDAO.findById(MOVIE_ID);

        assertTrue(maybeMovie.isPresent(), "Movie is missing!");
        assertEquals(maybeMovie.get().getTitle(), MOVIE_TITLE, "Movie title is wrong!");
        assertEquals(maybeMovie.get().getYear(), YEAR, "Movie year is wrong!");
        assertEquals(maybeMovie.get().getCountry(), COUNTRY, "Movie country is wrong!");
        assertEquals(maybeMovie.get().getGenre().getId(), GENRE, "Movie genre is wrong!");
    }

    @Test
    public void findAllMovies() {
        List<Movie> movies = movieDAO.findAll();

        assertTrue(movies.size() > 0, "Amount of movie titles is not correct!");
    }

    @Test()
    public void saveMovie() {
        movieDAO.save(NEW_MOVIE);
        assertEquals(movieDAO.findById(NEW_MOVIE.getId()).get().getTitle(), NEW_MOVIE.getTitle());
    }

    @Test(dependsOnMethods = {"saveMovie"})
    public void updateMovie() {
        // GIVEN
        var updatedMovie = NEW_MOVIE;
        var newYear = 2001;

        // WHEN
        updatedMovie.setYear(newYear);

        // THEN
        assertTrue(movieDAO.update(updatedMovie));
        assertEquals(movieDAO.findById(NEW_MOVIE.getId()).get().getYear().intValue(), newYear);
    }

    @Test(dependsOnMethods = {"updateMovie"})
    public void deleteMovie() {
        // GIVEN
        // WHEN
        var isDeleted = movieDAO.delete(NEW_MOVIE.getId());

        // THEN
        assertTrue(isDeleted);
        assertFalse(movieDAO.findById(NEW_MOVIE.getId()).isPresent());
    }
}
