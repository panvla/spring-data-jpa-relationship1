package com.vladimirpandurov.springdatarelationship.controller;

import com.vladimirpandurov.springdatarelationship.dto.requestDto.AuthorRequestDto;
import com.vladimirpandurov.springdatarelationship.dto.responseDto.AuthorResponseDto;
import com.vladimirpandurov.springdatarelationship.service.AuthorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/author")
@RequiredArgsConstructor
public class AuthorController {

    private final AuthorService authorService;

    @PostMapping
    public ResponseEntity<AuthorResponseDto> addAuthor(@RequestBody final AuthorRequestDto authorRequestDto){
        AuthorResponseDto authorResponseDto = this.authorService.addAuthor(authorRequestDto);
        return new ResponseEntity<>(authorResponseDto, HttpStatus.CREATED);

    }
    @GetMapping("/{id}")
    public ResponseEntity<AuthorResponseDto> getAuthor(@PathVariable("id") final Long id){
        AuthorResponseDto authorResponseDto = authorService.getAuthorById(id);
        return new ResponseEntity<>(authorResponseDto, HttpStatus.OK);
    }
    @GetMapping
    public ResponseEntity<List<AuthorResponseDto>> getAuthors(){
        List<AuthorResponseDto> authorResponseDtos = authorService.getAuthors();
        return new ResponseEntity<>(authorResponseDtos, HttpStatus.OK);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<AuthorResponseDto> deleteAuthor(@PathVariable("id") final Long id){
        AuthorResponseDto authorResponseDto = authorService.deleteAuthor(id);
        return new ResponseEntity<>(authorResponseDto, HttpStatus.OK);
    }
    @PutMapping
    private ResponseEntity<AuthorResponseDto> editAuthor(@PathVariable("id") final Long id,
                                                         @RequestBody final AuthorRequestDto authorRequestDto){
        AuthorResponseDto authorResponseDto = this.authorService.editAuthor(id, authorRequestDto);
        return new ResponseEntity<>(authorResponseDto, HttpStatus.OK);
    }
    @PostMapping("/addZipcode/{zipcodeId}/{authorId}")
    private ResponseEntity<AuthorResponseDto> addZipcode(@PathVariable("zipcodeId") final Long zipcodeId,
                                                         @PathVariable("authorId") final Long authorId){
        AuthorResponseDto authorResponseDto = this.authorService.addZipcodeToAuthor(authorId, zipcodeId);
        return new ResponseEntity<>(authorResponseDto, HttpStatus.OK);
    }
    @DeleteMapping("/removeZipcode/{id}")
    private ResponseEntity<AuthorResponseDto> removeZipcode(@PathVariable("id") final Long id){
        AuthorResponseDto authorResponseDto = this.authorService.deleteZipcodeFromAuthor(id);
        return new ResponseEntity<>(authorResponseDto, HttpStatus.OK);
    }


}
