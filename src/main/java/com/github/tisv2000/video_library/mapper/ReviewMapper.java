package com.github.tisv2000.video_library.mapper;

import com.github.tisv2000.video_library.dao.ReviewDao;
import com.github.tisv2000.video_library.dto.MovieReceivedDto;
import com.github.tisv2000.video_library.dto.ReviewCreatedDto;
import com.github.tisv2000.video_library.dto.ReviewReceiveDto;
import com.github.tisv2000.video_library.dto.UserReceivedDto;
import com.github.tisv2000.video_library.entity.*;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ReviewMapper {

    private static final ReviewMapper INSTANCE = new ReviewMapper();
    private static ReviewDao reviewDao = ReviewDao.getInstance();

    public static ReviewMapper getInstance() {
        return INSTANCE;
    }

    public Review mapFromReviewCreateDtoToEntity(ReviewCreatedDto reviewCreatedDto) {
        return Review.builder()
                .user(User.builder()
                        .id(Integer.valueOf(reviewCreatedDto.getUserId()))
                        .build())
                .movie(Movie.builder().id(Integer.valueOf(reviewCreatedDto.getMovieId())).build())
                .text(reviewCreatedDto.getText())
                .rate(Integer.valueOf(reviewCreatedDto.getRate()))
                .build();
    }

    public ReviewReceiveDto mapFromEntityToReviewReceiveDto(Review entity) {
        return ReviewReceiveDto.builder()
                .id(entity.getId())
                .user(UserReceivedDto.builder()
                        .id(entity.getUser().getId())
                        .name(entity.getUser().getName())
                        .birthday(entity.getUser().getBirthday())
                        .email(entity.getUser().getEmail())
                        .role(entity.getUser().getRole())
                        .gender(entity.getUser().getGender())
                        .build())
                .movie(MovieReceivedDto.builder()
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
