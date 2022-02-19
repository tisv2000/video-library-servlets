package com.github.tisv2000.video_library.service;

import com.github.tisv2000.video_library.dao.ReviewDao;
import com.github.tisv2000.video_library.dto.ReviewCreateDto;
import com.github.tisv2000.video_library.dto.ReviewReceiveDto;
import com.github.tisv2000.video_library.mapper.ReviewMapper;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.List;

import static java.util.stream.Collectors.toList;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ReviewService {

    private static final ReviewService INSTANCE = new ReviewService();
    private static ReviewMapper reviewMapper = ReviewMapper.getInstance();
    private static ReviewDao reviewDao = ReviewDao.getInstance();

    public static ReviewService getInstance() {
        return INSTANCE;
    }

    public Integer createReview(ReviewCreateDto reviewCreateDto) {
        var entity = reviewMapper.mapFromReviewCreateDtoToEntity(reviewCreateDto);
        reviewDao.save(entity);
        return entity.getId();
    }

    public List<ReviewReceiveDto> findAllWithMovieId(int movieId) {
        return reviewDao.findAllWithMovieId(movieId).stream()
                .map(entity -> reviewMapper.mapFromEntityToReviewReceiveDto(entity))
                .collect(toList());
    }

    public List<ReviewReceiveDto> findAllWithUserId(int userId) {
        return reviewDao.findAllWithUserId(userId).stream()
                .map(entity -> reviewMapper.mapFromEntityToReviewReceiveDto(entity))
                .collect(toList());
    }
}
