package com.github.tisv2000.videolibrary.mapper;

import com.github.tisv2000.videolibrary.dto.MovieReceivedDto;
import com.github.tisv2000.videolibrary.dto.ReviewCreatedDto;
import com.github.tisv2000.videolibrary.dto.ReviewReceiveDto;
import com.github.tisv2000.videolibrary.dto.UserReceivedDto;
import com.github.tisv2000.videolibrary.entity.Review;
import com.github.tisv2000.videolibrary.entity.User;
import com.github.tisv2000.videolibrary.entity.Movie;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ReviewMapper {

    private static final ReviewMapper INSTANCE = new ReviewMapper();

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

    public static ReviewMapper getInstance() {
        return INSTANCE;
    }
}
