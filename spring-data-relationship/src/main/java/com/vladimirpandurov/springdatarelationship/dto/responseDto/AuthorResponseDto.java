package com.vladimirpandurov.springdatarelationship.dto.responseDto;

import lombok.Data;

import java.util.List;

@Data
public class AuthorResponseDto {
    private Long id;
    private String name;
    private List<String> bookNames;
    private String zipCodeName;
}
