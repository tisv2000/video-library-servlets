package com.github.tisv2000.videolibrary.service;

import com.github.tisv2000.videolibrary.dao.ReviewDao;
import com.github.tisv2000.videolibrary.dto.ReviewCreatedDto;
import com.github.tisv2000.videolibrary.dto.ReviewReceiveDto;
import com.github.tisv2000.videolibrary.mapper.ReviewMapper;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.List;

import static java.util.stream.Collectors.toList;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ReviewService {

    private static final ReviewService INSTANCE = new ReviewService();
    private static final ReviewDao reviewDao = ReviewDao.getInstance();
    private static final ReviewMapper reviewMapper = ReviewMapper.getInstance();

    public Integer createReview(ReviewCreatedDto reviewCreatedDto) {
        var entity = reviewMapper.mapFromReviewCreateDtoToEntity(reviewCreatedDto);
        reviewDao.save(entity);
        return entity.getId();
    }

    public List<ReviewReceiveDto> findAllWithMovieId(int movieId) {
        return reviewDao.findAllByMovieId(movieId).stream()
                .map(entity -> reviewMapper.mapFromEntityToReviewReceiveDto(entity))
                .collect(toList());
    }

    public List<ReviewReceiveDto> findAllWithUserId(int userId) {
        return reviewDao.findAllByUserId(userId).stream()
                .map(entity -> reviewMapper.mapFromEntityToReviewReceiveDto(entity))
                .collect(toList());
    }

    public static ReviewService getInstance() {
        return INSTANCE;
    }
}
