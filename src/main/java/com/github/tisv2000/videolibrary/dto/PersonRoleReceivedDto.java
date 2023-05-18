package com.github.tisv2000.videolibrary.dto;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class PersonRoleReceivedDto {
    Integer id;
    String title;
}
