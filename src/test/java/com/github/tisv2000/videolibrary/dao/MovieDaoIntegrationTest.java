package com.github.tisv2000.videolibrary.dao;

import com.github.tisv2000.videolibrary.dto.MovieFilterDto;
import com.github.tisv2000.videolibrary.entity.Genre;
import com.github.tisv2000.videolibrary.entity.Movie;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Optional;

import static org.testng.Assert.*;

public class MovieDaoIntegrationTest {

    private final MovieDao movieDao = MovieDao.getInstance();

    public static final Integer MOVIE_ID = 3;
    public static final String MOVIE_TITLE = "The Vampire Diaries";
    public static final Integer YEAR = 2009;
    public static final String COUNTRY = "the USA";
    public static final Integer GENRE = 5;
    public static final String IMAGE = "vampireDiaries";
    public static final String DESCRIPTION = "On her first day at high school";

    public static final Movie newMovie = new Movie(
            null,
            "Gladiator",
            2000,
            "the USA",
            new Genre(
                    3,
                    "Action"
            ),
            "testImage",
            "Test"
    );

    @Test
    public void findMovieByIdPositiveTest() {
        // GIVEN
        // WHEN
        Optional<Movie> maybeMovie = movieDao.findById(MOVIE_ID);

        // THEN
        assertTrue(maybeMovie.isPresent(), "Movie is missing!");
        assertEquals(maybeMovie.get().getTitle(), MOVIE_TITLE, "Movie title is wrong!");
        assertEquals(maybeMovie.get().getYear(), YEAR, "Movie year is wrong!");
        assertEquals(maybeMovie.get().getCountry(), COUNTRY, "Movie country is wrong!");
        assertEquals(maybeMovie.get().getGenre().getId(), GENRE, "Movie genre is wrong!");
        assertEquals(maybeMovie.get().getImage(), IMAGE, "Movie image is wrong!");
        assertTrue(maybeMovie.get().getDescription().contains(DESCRIPTION), "Movie description is wrong!");
    }

    @Test
    public void findAllMovies() {
        // GIVEN
        // WHEN
        List<Movie> movies = movieDao.findAll();

        // THEN
        assertEquals(movies.size(), 16, "Amount of movie titles is not correct!");
    }

    @Test(dataProvider = "movieFilterDataProvider")
    public void findAllByFiltersTest(MovieFilterDto movieFilterDto, int expectedAmountOfMovies) {
        // GIVEN
        // WHEN
        var movies = movieDao.findAllByFilters(movieFilterDto);

        // THEN
        assertEquals(movies.size(), expectedAmountOfMovies,
                "Wrong amount of movies. Expected " + expectedAmountOfMovies + ", but found " + movies.size());
    }

    @Test()
    public void saveMovie() {
        // GIVEN
        // WHEN
        movieDao.save(newMovie);

        // THEN
        assertNotNull(newMovie.getId());
    }

    @Test(dependsOnMethods = {"saveMovie"})
    public void updateMovie() {
        // GIVEN
        var updatedMovie = newMovie;
        var newYear = 2001;

        // WHEN
        updatedMovie.setYear(newYear);

        // THEN
        assertTrue(movieDao.update(updatedMovie));
        assertEquals(movieDao.findById(newMovie.getId()).get().getYear().intValue(), newYear);
    }

    @Test(dependsOnMethods = {"updateMovie"})
    public void deleteMovie() {
        // GIVEN
        // WHEN
        var isDeleted = movieDao.delete(newMovie.getId());

        // THEN
        assertTrue(isDeleted);
        assertFalse(movieDao.findById(newMovie.getId()).isPresent());
    }

    @DataProvider(name = "movieFilterDataProvider")
    public static Object[][] movieFilterDataProvider() {
        return new Object[][]{
                {buildMovieFilterDto("The Holiday", "2006", "the USA", "7"), 1},
                {buildMovieFilterDto("", "2006", "the USA", "7"), 1},
                {buildMovieFilterDto("The Holiday", "", "the USA", "7"), 1},
                {buildMovieFilterDto("The Holiday", "2006", "", "7"), 1},
                {buildMovieFilterDto("The Holiday", "2006", "the USA", ""), 1},
                {buildMovieFilterDto(null, "2006", "the USA", ""), 1},
                {buildMovieFilterDto("", "", "", ""), 16},
                {buildMovieFilterDto("Some test movie", "", "", ""), 0},
        };
    }

    private static MovieFilterDto buildMovieFilterDto(String title, String year, String country, String genreId) {
        return MovieFilterDto.builder()
                .title(title)
                .year(year)
                .country(country)
                .genre(genreId)
                .build();
    }
}
