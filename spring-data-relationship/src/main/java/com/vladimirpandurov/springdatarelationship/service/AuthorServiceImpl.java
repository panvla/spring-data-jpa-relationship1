package com.vladimirpandurov.springdatarelationship.service;

import com.vladimirpandurov.springdatarelationship.dto.Mapper;
import com.vladimirpandurov.springdatarelationship.dto.requestDto.AuthorRequestDto;
import com.vladimirpandurov.springdatarelationship.dto.responseDto.AuthorResponseDto;
import com.vladimirpandurov.springdatarelationship.model.Author;
import com.vladimirpandurov.springdatarelationship.model.Zipcode;
import com.vladimirpandurov.springdatarelationship.repository.AuthorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@RequiredArgsConstructor
public class AuthorServiceImpl implements AuthorService{

    private final AuthorRepository authorRepository;
    private final ZipcodeService zipcodeService;

    @Transactional
    @Override
    public AuthorResponseDto addAuthor(AuthorRequestDto authorRequestDto) {
        Author author = new Author();
        author.setName(authorRequestDto.getName());
        if(authorRequestDto.getZipCodeId() == null){
            throw new IllegalArgumentException("author need a zipcode");
        }
        Zipcode zipcode = zipcodeService.getZipcode(authorRequestDto.getZipCodeId());
        author.setZipcode(zipcode);
        authorRepository.save(author);
        return Mapper.authorToAuthorResponseDto(author);
    }

    @Override
    public List<AuthorResponseDto> getAuthors() {
        List<Author> authors = StreamSupport
                .stream(authorRepository.findAll().spliterator(), false)
                .collect(Collectors.toList());
        return Mapper.authorsToAuthorResponseDtos(authors);
    }

    @Override
    public AuthorResponseDto getAuthorById(Long authorId) {
        return Mapper.authorToAuthorResponseDto(getAuthor(authorId));
    }

    @Override
    public Author getAuthor(Long authorId) {
        Author author = authorRepository.findById(authorId).orElseThrow(()-> new IllegalArgumentException("No such author"));
        return author;
    }


    @Override
    public AuthorResponseDto deleteAuthor(Long authorId) {
        Author author = getAuthor(authorId);
        authorRepository.delete(author);
        return Mapper.authorToAuthorResponseDto(author);
    }
    @Transactional
    @Override
    public AuthorResponseDto editAuthor(Long authorId, AuthorRequestDto authorRequestDto) {
        Author authorToEdit = getAuthor(authorId);
        authorToEdit.setName(authorRequestDto.getName());
        if(authorRequestDto.getZipCodeId() != null){
            Zipcode zipCode = zipcodeService.getZipcode(authorRequestDto.getZipCodeId());
            authorToEdit.setZipcode(zipCode);
        }
        return Mapper.authorToAuthorResponseDto(authorToEdit);
    }
    @Transactional
    @Override
    public AuthorResponseDto addZipcodeToAuthor(Long authorId, Long zipcodeId) {
        Author author = getAuthor(authorId);
        Zipcode zipcode = zipcodeService.getZipcode(zipcodeId);
        if(Objects.nonNull(author.getZipcode())){
            throw new RuntimeException("author already has a zipcode");
        }
        author.setZipcode(zipcode);
        return Mapper.authorToAuthorResponseDto(author);
    }
    @Transactional
    @Override
    public AuthorResponseDto deleteZipcodeFromAuthor(Long authorId) {
        Author author = getAuthor(authorId);
        author.setZipcode(null);
        return Mapper.authorToAuthorResponseDto(author);
    }
}
