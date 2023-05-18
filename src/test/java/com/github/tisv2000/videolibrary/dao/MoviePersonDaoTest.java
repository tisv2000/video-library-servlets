package com.github.tisv2000.videolibrary.dao;

import com.github.tisv2000.videolibrary.entity.Movie;
import com.github.tisv2000.videolibrary.entity.MoviePerson;
import com.github.tisv2000.videolibrary.entity.Person;
import com.github.tisv2000.videolibrary.entity.PersonRole;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;

public class MoviePersonDaoTest {

    private final MoviePersonDao underTest = MoviePersonDao.getInstance();

    @Test
    public void testSave() {
        // GIVEN
        var moviePerson = MoviePerson.builder()
                .movie(Movie.builder()
                        .id(3)
                        .build())
                .person(Person.builder()
                        .id(2)
                        .build())
                .role(PersonRole.builder()
                        .id(3)
                        .build())
                .build();

        // WHEN
        underTest.save(moviePerson);

        // THEN
        assertNotNull(moviePerson.getId(), "MoviePerson was not saved successfully!");
    }

    @Test
    public void findAllByMovieIdTest() {
        // GIVEN
        // WHEN
        var moviePersons = underTest.findAllByMovieId(3);

        // THEN
        assertEquals(moviePersons.size(), 3, "MoviePersons amount is incorrect!");
    }
}
