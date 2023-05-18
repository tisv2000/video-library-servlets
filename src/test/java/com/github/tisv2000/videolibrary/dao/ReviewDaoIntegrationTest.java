package com.github.tisv2000.videolibrary.dao;

import com.github.tisv2000.videolibrary.entity.Movie;
import com.github.tisv2000.videolibrary.entity.Review;
import com.github.tisv2000.videolibrary.entity.User;
import org.testng.annotations.Test;

import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertTrue;

public class ReviewDaoIntegrationTest {

    private final ReviewDao reviewDao = ReviewDao.getInstance();

    @Test
    public void saveTest() {
        // GIVEN
        var review = Review.builder()
                .movie(Movie.builder()
                        .id(2)
                        .build())
                .user(User.builder()
                        .id(1)
                        .build())
                .rate(10)
                .text("Amazing movie!")
                .build();

        // WHEN
        reviewDao.save(review);

        // THEN
        assertNotNull(review.getId());
    }

    @Test
    public void findAllTest() {
        // GIVEN
        // WHEN
        var reviews = reviewDao.findAll();

        // THEN
        assertTrue(reviews.size() > 0);
    }

    @Test
    public void findAllByMovieIdTest() {
        // GIVEN
        // WHEN
        var reviews = reviewDao.findAllByMovieId(3);

        // THEN
        assertTrue(reviews.size() > 0);
    }

    @Test
    public void findAllByUserIdTest() {
        // GIVEN
        // WHEN
        var reviews = reviewDao.findAllByUserId(1);

        // THEN
        assertTrue(reviews.size() > 0);
    }
}
