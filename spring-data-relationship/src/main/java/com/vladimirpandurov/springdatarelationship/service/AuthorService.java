package com.vladimirpandurov.springdatarelationship.service;

import com.vladimirpandurov.springdatarelationship.dto.requestDto.AuthorRequestDto;
import com.vladimirpandurov.springdatarelationship.dto.responseDto.AuthorResponseDto;
import com.vladimirpandurov.springdatarelationship.model.Author;

import java.util.List;

public interface AuthorService {
    AuthorResponseDto addAuthor(AuthorRequestDto authorRequestDto);
    List<AuthorResponseDto> getAuthors();
    AuthorResponseDto getAuthorById(Long authorId);
    Author getAuthor(Long authorId);
    AuthorResponseDto deleteAuthor(Long authorId);
    AuthorResponseDto editAuthor(Long authorId, AuthorRequestDto authorRequestDto);
    AuthorResponseDto addZipcodeToAuthor(Long authorId, Long zipcodeId);
    AuthorResponseDto deleteZipcodeFromAuthor(Long authorId);
}
