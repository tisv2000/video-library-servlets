package com.github.tisv2000.videolibrary.dto;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class ReviewReceiveDto {
    Integer id;
    UserReceivedDto user;
    MovieReceivedDto movie;
    String text;
    Integer rate;
}
