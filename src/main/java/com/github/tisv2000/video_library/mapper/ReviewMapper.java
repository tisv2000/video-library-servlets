package com.github.tisv2000.video_library.mapper;

import com.github.tisv2000.video_library.dao.ReviewDao;
import com.github.tisv2000.video_library.dto.MovieReceiveDto;
import com.github.tisv2000.video_library.dto.ReviewCreateDto;
import com.github.tisv2000.video_library.dto.ReviewReceiveDto;
import com.github.tisv2000.video_library.dto.UserReceiveDto;
import com.github.tisv2000.video_library.entity.*;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ReviewMapper {

    private static final ReviewMapper INSTANCE = new ReviewMapper();
    private static ReviewDao reviewDao = ReviewDao.getInstance();

    public static ReviewMapper getInstance() {
        return INSTANCE;
    }

    public Review mapFromReviewCreateDtoToEntity(ReviewCreateDto reviewCreateDto) {
        return Review.builder()
                .user(User.builder()
                        .id(Integer.valueOf(reviewCreateDto.getUserId()))
                        .build())
                .movie(Movie.builder().id(Integer.valueOf(reviewCreateDto.getMovieId())).build())
                .text(reviewCreateDto.getText())
                .rate(Integer.valueOf(reviewCreateDto.getRate()))
                .build();
    }

    public ReviewReceiveDto mapFromEntityToReviewReceiveDto(Review entity) {
        return ReviewReceiveDto.builder()
                .id(entity.getId())
                .user(UserReceiveDto.builder()
                        .id(entity.getUser().getId())
                        .name(entity.getUser().getName())
                        .birthday(entity.getUser().getBirthday())
                        .email(entity.getUser().getEmail())
                        .role(entity.getUser().getRole())
                        .gender(entity.getUser().getGender())
                        .build())
                .movie(MovieReceiveDto.builder()
                        .id(entity.getMovie().getId())
                        .title(entity.getMovie().getTitle())
                        .year(entity.getMovie().getYear())
                        .country(entity.getMovie().getCountry())
                        .description(entity.getMovie().getDescription())
                        .build())
                .text(entity.getText())
                .rate(entity.getRate())
                .build();
    }
}
