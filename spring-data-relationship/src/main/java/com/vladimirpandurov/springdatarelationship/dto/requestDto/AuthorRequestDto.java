package com.vladimirpandurov.springdatarelationship.dto.requestDto;

import lombok.Data;

@Data
public class AuthorRequestDto {
    private String name;
    private Long zipCodeId;
}
