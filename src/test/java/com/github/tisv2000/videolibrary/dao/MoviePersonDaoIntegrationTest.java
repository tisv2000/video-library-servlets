package com.github.tisv2000.videolibrary.dao;

import com.github.tisv2000.videolibrary.entity.Movie;
import com.github.tisv2000.videolibrary.entity.MoviePerson;
import com.github.tisv2000.videolibrary.entity.Person;
import com.github.tisv2000.videolibrary.entity.PersonRole;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;

public class MoviePersonDaoIntegrationTest {

    private final MoviePersonDao moviePersonDao = MoviePersonDao.getInstance();

    @Test
    public void testSave() {
        // GIVEN
        int movieId = 3;
        int personId = 2;
        int roleId = 3;
        var moviePerson = MoviePerson.builder()
                .movie(Movie.builder()
                        .id(movieId)
                        .build())
                .person(Person.builder()
                        .id(personId)
                        .build())
                .role(PersonRole.builder()
                        .id(roleId)
                        .build())
                .build();

        // WHEN
        moviePersonDao.save(moviePerson);

        // THEN
        assertNotNull(moviePerson.getId(), "MoviePerson was not saved successfully!");
        moviePersonDao.delete(moviePerson.getId());
    }

    @Test
    public void findAllByMovieIdTest() {
        // GIVEN
        // WHEN
        var moviePersons = moviePersonDao.findAllByMovieId(3);

        // THEN
        assertEquals(moviePersons.size(), 6, "MoviePersons amount is incorrect!");
    }
}
